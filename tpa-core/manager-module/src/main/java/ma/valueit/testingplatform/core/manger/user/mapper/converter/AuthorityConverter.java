package ma.valueit.testingplatform.core.manger.user.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.AuthorityDto;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityEntity;
import ma.valueit.testingplatform.core.service.profile.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorityConverter extends AbstractListConverter<AuthorityEntity, AuthorityDto> {
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AuthorityCategoryConverter authorityCategoryConverter;

    @Override
    public AuthorityEntity convertFrom(AuthorityDto source) {
        if (source == null) {
            return null;
        }

        AuthorityEntity target = null;

        if (source.getId() != null) {
            target = authorityService.findById(source.getId());
        } else {
            target = new AuthorityEntity();
        }

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategory(authorityCategoryConverter.convertFrom(source.getCategory()));

        return target;
    }

    @Override
    public AuthorityDto convertTo(AuthorityEntity source) {
        if (source == null) {
            return null;
        }

        AuthorityDto target = new AuthorityDto();

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategory(authorityCategoryConverter.convertTo(source.getCategory()));

        return target;
    }
}
