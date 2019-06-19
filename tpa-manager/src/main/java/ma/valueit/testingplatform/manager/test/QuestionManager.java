package ma.valueit.testingplatform.manager.test;

import ma.valueit.testingplatform.core.manger.CrudManager;
import ma.valueit.testingplatform.manager.test.mapper.converter.QuestionConverter;
import ma.valueit.testingplatform.model.question.QuestionEntity;
import ma.valueit.testingplatform.service.test.QuestionService;
import ma.valueit.tpa.manager.test.mapper.dto.QuestionDto;

public interface QuestionManager extends CrudManager<QuestionDto, Integer, QuestionEntity, QuestionService, QuestionConverter> {
}
