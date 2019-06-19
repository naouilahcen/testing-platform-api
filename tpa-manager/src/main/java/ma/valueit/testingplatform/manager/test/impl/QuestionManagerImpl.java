package ma.valueit.testingplatform.manager.test.impl;

import ma.valueit.testingplatform.core.manger.impl.CrudManagerImpl;
import ma.valueit.testingplatform.manager.test.QuestionManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.QuestionConverter;
import ma.valueit.testingplatform.model.question.QuestionEntity;
import ma.valueit.testingplatform.service.test.QuestionService;
import ma.valueit.tpa.manager.test.mapper.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionManagerImpl extends CrudManagerImpl<QuestionDto , Integer, QuestionEntity, QuestionService , QuestionConverter> implements QuestionManager {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionConverter questionConverter;

    @Override
    public QuestionService getService(){
        return questionService;
    }
    @Override
    public QuestionConverter getConverter(){
        return questionConverter;
    }

}

