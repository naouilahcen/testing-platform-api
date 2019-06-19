package ma.valueit.testingplatform.model.result;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.model.candidate.SessionEntity;
import ma.valueit.testingplatform.model.exam.ExamEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resultat")
public class ResultEntity implements Serializable {

    @Getter @Setter
    @Column(name = "nbr_questions_correct")
    private Integer questionsCorrect;

    @Getter
    @Setter
    @Column(name = "score")
    private String score;

    @Id
    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Id
    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

}
