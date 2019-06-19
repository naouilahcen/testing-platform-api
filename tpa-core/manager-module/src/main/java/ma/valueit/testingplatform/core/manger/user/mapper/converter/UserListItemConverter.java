package ma.valueit.testingplatform.core.manger.user.mapper.converter;


import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserListItemDto;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Created by valueit-mac-yelansari on 4/2/18.
 */
@Component
public class UserListItemConverter extends AbstractListConverter<UserEntity, UserListItemDto> {
    @Override
    public UserListItemDto convertTo(UserEntity source) {
        if (source == null) {
            return null;
        }

        UserListItemDto target = new UserListItemDto();

        target.setId(source.getId());
        target.setPhone(source.getPhone());
        target.setAddress(source.getAddress());

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

        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setAdmin(source.isAdmin());
        target.setEnabled(source.isEnabled());

        if (source.getProfile() != null) {
            target.setProfile(source.getProfile().getTitle());
        }

        return target;
    }

    @Override
    public UserEntity convertFrom(UserListItemDto source) {
        return null;
    }
}
