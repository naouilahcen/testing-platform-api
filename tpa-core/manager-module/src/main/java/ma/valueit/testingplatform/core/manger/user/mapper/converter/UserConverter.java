package ma.valueit.testingplatform.core.manger.user.mapper.converter;

import ma.valueit.testingplatform.core.errorhandling.businessexception.BusinessException;
import ma.valueit.testingplatform.core.manger.exception.UserManagementErrorCode;
import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.profile.ProfileService;
import ma.valueit.testingplatform.core.service.profile.UserService;
import ma.valueit.testingplatform.core.utils.ByteUtils;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;

@Component
public class UserConverter extends AbstractListConverter<UserEntity, UserDto> {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthorityConverter authorityConverter;

    @Override
    public UserDto convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }

        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setPhone(source.getPhone());
        target.setMobile(source.getMobile());
        target.setAddress(source.getAddress());
        target.setAvatar(ByteUtils.toString(source.getAvatar()));

        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEnabled(source.isEnabled());
        target.setAuthType(source.getAuthType());

        if (source.getProfile() != null) {
            target.setProfileId(source.getProfile().getId());
        }

        List<Integer> excludedAuthorities = authorityConverter.convertToIds(source.getExcludedAuthorities());

        if(!CollectionUtils.isEmpty(excludedAuthorities)){
            target.setExcludedAuthoritiesIds(new LinkedHashSet<>(excludedAuthorities));
        }

        if (source.getCreatedBy() != null && source.getCreatedBy().isPresent()) {
            target.setCreatedBy(source.getCreatedBy().get());
        }

        if (source.getLastModifiedBy() != null && source.getLastModifiedBy().isPresent()) {
            target.setLastModifiedBy(source.getLastModifiedBy().get());
        }

        if (source.getCreatedDate() != null && source.getCreatedDate().isPresent()) {
            target.setCreatedDate(source.getCreatedDate().get());
        }

        if (source.getLastModifiedDate() != null && source.getLastModifiedDate().isPresent()) {
            target.setLastModifiedDate(source.getLastModifiedDate().get());
        }

        return target;
    }

    @Override
    public UserEntity convertFrom(UserDto source) {
        if (source == null) {
            return null;
        }

        UserEntity target = null;
        if (source.getId() != null) {
            target = userService.findById(source.getId());
            if (target == null) {
                throw new BusinessException(UserManagementErrorCode.NOT_FOUND);
            }
        } else {
            target = new UserEntity();
        }

        target.setId(source.getId());
        target.setPhone(source.getPhone());
        target.setMobile(source.getMobile());
        target.setAddress(source.getAddress());
        target.setAvatar(ByteUtils.toByte(source.getAvatar()));
        target.setUsername(source.getUsername());
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEmail(source.getEmail());
        target.setEnabled(source.isEnabled());
        target.setAdmin(source.isAdmin());

        if (source.getAuthType() == null) {
            target.setAuthType(AuthTypeEnum.SIMPLE);
        } else {
            target.setAuthType(source.getAuthType());
        }

        if (source.getProfileId() != null) {
            ProfileEntity oldProfile = target.getProfile();

            if (oldProfile != null) {
                oldProfile = profileService.initializeAndUnProxy(oldProfile);
            }

            if (oldProfile == null || (oldProfile != null && !oldProfile.getId().equals(source.getProfileId()))) {
                ProfileEntity profile = profileService.findById(source.getProfileId());

                profile = profileService.initializeAndUnProxy(profile);

                target.setProfile(profile);
            }
        }

        if(CollectionUtils.isEmpty(source.getExcludedAuthoritiesIds())){
            target.setExcludedAuthorities(null);
        }

        return target;
    }
}
