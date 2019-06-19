package ma.valueit.testingplatform.manager.test.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ExampleDto;
import ma.valueit.testingplatform.model.test.ExampleEntity;
import ma.valueit.testingplatform.service.test.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleConverter extends AbstractListConverter<ExampleEntity, ExampleDto> {
    @Autowired
    private ExampleService exampleService;

    @Override
    public ExampleEntity convertFrom(ExampleDto source) {
        if (source == null) {
            return null;
        }

        ExampleEntity target = null;

        if (source.getId() != null) {
            target = exampleService.findById(source.getId());
        } else {
            target = new ExampleEntity();
        }

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }

    @Override
    public ExampleDto convertTo(ExampleEntity source) {
        if (source == null) {
            return null;
        }

        ExampleDto target = new ExampleDto();

        if (source.getCreatedDate() != null && source.getCreatedDate().isPresent()) {
            target.setCreatedDate(source.getCreatedDate().get());
        }

        if (source.getLastModifiedBy() != null && source.getLastModifiedBy().isPresent()) {
            target.setLastModifiedBy(source.getLastModifiedBy().get());
        }

        if (source.getLastModifiedDate() != null && source.getLastModifiedDate().isPresent()) {
            target.setLastModifiedDate(source.getLastModifiedDate().get());
        }

        if (source.getCreatedBy() != null && source.getCreatedBy().isPresent()) {
            target.setCreatedBy(source.getCreatedBy().get());
        }

        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());

        return target;
    }
}
