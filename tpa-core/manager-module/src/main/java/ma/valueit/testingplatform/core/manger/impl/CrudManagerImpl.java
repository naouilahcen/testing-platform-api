package ma.valueit.testingplatform.core.manger.impl;

import ma.valueit.testingplatform.core.errorhandling.businessexception.*;
import ma.valueit.testingplatform.core.errorhandling.exception.CommonErrorCode;
import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.core.manger.mapper.converters.ListConverter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.core.manger.user.mapper.converter.UserConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.BasicService;
import ma.valueit.testingplatform.core.service.mail.MailService;
import ma.valueit.testingplatform.core.service.profile.ProfileService;
import ma.valueit.testingplatform.core.service.profile.UserService;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import ma.valueit.testingplatform.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Service
public abstract class CrudManagerImpl<DTO extends DtoWithID<ID>, ID extends Serializable, ENTITY extends CustomAuditable<String, ID, LocalDate>, SERVICE extends BasicService<ENTITY, ID>, CONVERTER extends ListConverter<ENTITY, DTO>> implements CrudManager<DTO, ID, ENTITY, SERVICE, CONVERTER> {
    @Autowired
    protected UserService userService;

    @Autowired
    protected ProfileService profileService;

    @Autowired
    protected UserConverter userConverter;

    @Autowired
    protected MailService mailService;

    protected UserDto currentUser;

    @Override
    public Page<DTO> findAll(Integer page, Integer size) {
        page = (page == null || page < 1 ? 0 : page - 1);
        size = (size == null || size < 1 ? 0 : size);

        Pageable pageRequest = PageRequest.of(page, size);

        Page<ENTITY> pageOfEntities = null;

        pageOfEntities = this.getService().findAll(pageRequest);

        if (pageOfEntities == null || CollectionUtils.isEmpty(pageOfEntities.getContent())) {
            throw new NoContentException();
        }

        return pageOfEntities.map(source -> getConverter().convertTo(source));

    }

    @Override
    public List<DTO> findAll() {
        List<ENTITY> entities = this.getService().findAll();

        List<DTO> result = getConverter().convertTo(entities);

        return result;
    }

    @Override
    public DTO find(ID id) {
        ENTITY entity = this.getService().findById(id);

        if (entity == null) {
            throw new EntityNotFoundException();
        }

        DTO result = getConverter().convertTo(entity);

        return result;
    }

    @Override
    public DTO create(DTO dto) {
        if (dto == null) {
            throw new InvalidPayloadException();
        }

        dto.setId(null);

        dto.validate();

        ENTITY entity = getConverter().convertFrom(dto);

        entity = this.getService().save(entity);

        DTO result = getConverter().convertTo(entity);

        return result;
    }

    @Override
    public DTO edit(ID id, DTO dto) {
        if (StringUtils.isEmpty(id)) {
            throw new MissingIdException();
        }

        if (dto == null) {
            throw new InvalidPayloadException();
        }

        if (!id.equals(dto.getId())) {
            throw new BusinessException(CommonErrorCode.TRYING_TO_EDIT_ANOTHER_ENTITY);
        }

        dto.validate();

        ENTITY entity = this.getService().findById(id);

        if (entity == null) {
            throw new EntityNotFoundException();
        }

        entity = getConverter().convertFrom(dto);

        entity = this.getService().save(entity);

        dto = getConverter().convertTo(entity);

        return dto;
    }

    @Override
    public void delete(ID id) {
        if (StringUtils.isEmpty(id)) {
            throw new MissingIdException();
        }

        ENTITY entity = this.getService().findById(id);

        if (entity == null) {
            throw new EntityNotFoundException();
        }

        try {
            this.getService().deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityConstraintViolationException();
        }
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            UserEntity userEntity = this.userService.findByUsername(currentUserName);

            return userConverter.convertTo(userEntity);
        }

        return null;
    }

    @Override
    public void isUserLogged() {
        this.currentUser = this.getCurrentUser();

        if (this.currentUser == null) {
            throw new UnAuthorizedException();
        }
    }

    @Override
    public abstract SERVICE getService();

    @Override
    public abstract CONVERTER getConverter();
}
