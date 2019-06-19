package ma.valueit.testingplatform.core.manger.user.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.AuthorityCategoryDto;
import ma.valueit.testingplatform.core.model.entity.user.AuthorityCategoryEntity;
import ma.valueit.testingplatform.core.service.profile.AuthorityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorityCategoryConverter extends AbstractListConverter<AuthorityCategoryEntity, AuthorityCategoryDto> {
    @Autowired
    private AuthorityCategoryService authorityCategoryService;

    @Override
    public AuthorityCategoryEntity convertFrom(AuthorityCategoryDto source) {
        if (source == null) {
            return null;
        }

        AuthorityCategoryEntity target = null;

        if (source.getId() != null) {
            target = authorityCategoryService.findById(source.getId());
        } else {
            target = new AuthorityCategoryEntity();
        }

        target.setId(source.getId());
        target.setTitle(source.getTitle());

        return target;
    }

    @Override
    public AuthorityCategoryDto convertTo(AuthorityCategoryEntity source) {
        if (source == null) {
            return null;
        }

        AuthorityCategoryDto target = new AuthorityCategoryDto();

        target.setId(source.getId());
        target.setTitle(source.getTitle());

        return target;
    }
}
