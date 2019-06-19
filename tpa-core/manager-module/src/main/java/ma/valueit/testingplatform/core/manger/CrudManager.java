package ma.valueit.testingplatform.core.manger;

import ma.valueit.testingplatform.core.manger.mapper.converters.ListConverter;
import ma.valueit.testingplatform.core.manger.mapper.dto.DtoWithID;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.service.BasicService;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface CrudManager<DTO extends DtoWithID<ID>, ID extends Serializable, ENTITY extends CustomAuditable<String, ID, LocalDate>, SERVICE extends BasicService<ENTITY, ID>, CONVERTER extends ListConverter<ENTITY, DTO>> {
    List<DTO> findAll();

    Page<DTO> findAll(Integer page, Integer size);

    DTO find(ID id);

    DTO create(DTO dto);

    DTO edit(ID id, DTO dto);

    void delete(ID id);

    void isUserLogged();

    UserDto getCurrentUser();

    SERVICE getService();

    CONVERTER getConverter();
}
