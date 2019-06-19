package ma.valueit.testingplatform.manager.test.impl;

import ma.valueit.testingplatform.core.manger.impl.CrudManagerImpl;
import ma.valueit.testingplatform.manager.test.ExampleManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.ExampleConverter;
import ma.valueit.testingplatform.manager.test.mapper.dto.ExampleDto;
import ma.valueit.testingplatform.model.test.ExampleEntity;
import ma.valueit.testingplatform.service.test.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleManagerImpl extends CrudManagerImpl<ExampleDto, Integer, ExampleEntity, ExampleService, ExampleConverter> implements ExampleManager {
    @Autowired
    private ExampleService exampleService;

    @Autowired
    private ExampleConverter exampleConverter;

    @Override
    public ExampleService getService() {
        return exampleService;
    }

    @Override
    public ExampleConverter getConverter() {
        return exampleConverter;
    }
}
