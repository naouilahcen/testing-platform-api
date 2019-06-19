package ma.valueit.testingplatform.rest;

import ma.valueit.testingplatform.core.rest.CrudResource;
import ma.valueit.testingplatform.manager.test.ExampleManager;
import ma.valueit.testingplatform.manager.test.mapper.dto.ExampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examples")
public class ExampleResource extends CrudResource<ExampleDto, Integer, ExampleManager> {

    @Autowired
    private ExampleManager exampleManager;

    @Override
    protected ExampleManager getCrudManager() {
        return exampleManager;
    }
}
