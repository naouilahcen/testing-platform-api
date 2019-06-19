package ma.valueit.testingplatform.core.manger.user.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.ProfileDto;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.model.entity.user.ProfileEntity;
import ma.valueit.testingplatform.core.service.profile.ProfileService;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by yelansari on 1/28/18.
 */
@Component
public class ProfileConverter extends AbstractListConverter<ProfileEntity, ProfileDto> {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private AuthorityConverter authorityConverter;

    @Override
    public ProfileDto convertTo(ProfileEntity source) {
        if (source == null) {
            return null;
        }

        ProfileDto target = new ProfileDto();

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        target.setAuthorities(authorityConverter.convertTo(source.getAuthorities()));

        return target;
    }


    @Override
    public ProfileEntity convertFrom(ProfileDto source) {
        if (source == null) {
            return null;
        }

        ProfileEntity target = null;
        if (source.getId() != null) {
            target = profileService.findById(source.getId());
        } else {
            target = new ProfileEntity();
        }

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        List<AuthorityEntity> authorities = authorityConverter.convertFrom(source.getAuthorities());
        if (!CollectionUtils.isEmpty(authorities)) {
            target.setAuthorities(new LinkedHashSet<>(authorities));
        }
        return target;
    }
}
