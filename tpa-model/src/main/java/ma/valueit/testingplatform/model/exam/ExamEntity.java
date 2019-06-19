package ma.valueit.testingplatform.model.exam;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.candidate.SessionEntity;
import ma.valueit.testingplatform.model.question.QuestionEntity;
import ma.valueit.testingplatform.model.result.ResultEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "examen")
public class ExamEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String titre;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer nbrQuestions ;

    @OneToMany(
            mappedBy = "exam",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<ResultEntity> results = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examenNature_id")
    @Getter @Setter
    private ExamNatureEntity examenNature;



    @ManyToMany
    private List<QuestionEntity>questions;



}
