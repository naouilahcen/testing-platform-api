package ma.valueit.testingplatform.manager.test.mapper.converter;

import ma.valueit.testingplatform.core.manger.mapper.converters.AbstractListConverter;
import ma.valueit.testingplatform.model.question.QuestionEntity;
import ma.valueit.testingplatform.service.test.QuestionService;
import ma.valueit.tpa.manager.test.mapper.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverter extends AbstractListConverter<QuestionEntity, QuestionDto> {

    @Autowired
    private QuestionService questionService;



    public QuestionEntity convertFrom(QuestionDto source ) {
        if (source == null) {
            return null;
        }

        QuestionEntity target = null;
        if (source.getId() != null) {
            target = questionService.findById(source.getId());
        } else {
            target = new QuestionEntity();
        }

       /* if (!CollectionUtils.isEmpty(source.getResponseDtos())) {
            target.setResponse(responseConverter.convertFrom(source.getResponseDtos()));
        }*/

        target.setId(source.getId());
        target.setStatement(source.getEnonce());// jai regler ce prblm
        target.setDescription(source.getDescription());
//        target.setResponse(source.getResponse());

            return target;
    }
    @Override
    public QuestionDto convertTo(QuestionEntity source) {
        if (source == null) {
            return null;
        }

        QuestionDto target = new QuestionDto();

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

       /* if (!CollectionUtils.isEmpty(source.getResponse())) {
            target.setResponseDtos(responseConverter.convertTo(source.getResponse()));
        }*/
        target.setId(source.getId());
        target.setEnonce(source.getStatement());// jai regler ce prblm
        target.setDescription(source.getDescription());
//        target.setResponse(source.getResponse());

        return target;
    }


}
