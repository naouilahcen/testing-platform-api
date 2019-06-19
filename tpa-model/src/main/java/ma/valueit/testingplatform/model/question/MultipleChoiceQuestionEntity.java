package ma.valueit.testingplatform.model.question;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "multiple_choice_questions")
public class MultipleChoiceQuestionEntity extends QuestionEntity {

    @OneToMany(

            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OptionEntity> options = new ArrayList<>();

}