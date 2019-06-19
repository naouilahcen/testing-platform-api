package ma.valueit.testingplatform.manager.test;

import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.ExampleConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ExampleDto;
import ma.valueit.testingplatform.model.test.ExampleEntity;
import ma.valueit.testingplatform.service.test.ExampleService;

public interface ExampleManager extends CrudManager<ExampleDto, Integer, ExampleEntity, ExampleService, ExampleConverter> {
}
