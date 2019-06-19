package ma.valueit.testingplatform.service.test.impl;

import ma.valueit.testingplatform.core.repository.BasicRepository;
import ma.valueit.testingplatform.core.service.BasicServiceImpl;
import ma.valueit.testingplatform.model.question.QuestionEntity;
import ma.valueit.testingplatform.repository.test.QuestionRepository;
import ma.valueit.testingplatform.service.test.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends BasicServiceImpl<QuestionEntity ,Integer> implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public BasicRepository<QuestionEntity,Integer> getRepository() {
        return questionRepository;
    }
}
