package ma.valueit.testingplatform.service.test.impl;


import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.model.test.ExampleEntity;
import ma.valueit.testingplatform.repository.test.ExampleRepository;
import ma.valueit.testingplatform.service.test.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl  extends BasicServiceImpl<ExampleEntity, Integer> implements ExampleService {
    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public BasicRepository<ExampleEntity, Integer> getRepository() {
        return exampleRepository;
    }
}
