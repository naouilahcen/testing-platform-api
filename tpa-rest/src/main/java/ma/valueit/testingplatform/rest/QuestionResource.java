package ma.valueit.testingplatform.rest;


import ma.valueit.testingplatform.core.rest.CrudResource;
import ma.valueit.testingplatform.manager.test.QuestionManager;
import ma.valueit.tpa.manager.test.mapper.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionResource extends CrudResource<QuestionDto, Integer, QuestionManager> {

    @Autowired
    private QuestionManager questionManager;

    @Override
    protected QuestionManager getCrudManager() {
        return questionManager;
    }
}
