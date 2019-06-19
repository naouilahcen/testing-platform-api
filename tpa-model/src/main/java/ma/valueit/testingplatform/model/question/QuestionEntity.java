package ma.valueit.testingplatform.model.question;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.attachment.AttachmentEntity;
import ma.valueit.testingplatform.model.exam.ExamEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)
public class QuestionEntity extends CustomAuditable<String , Integer , LocalDate> {

    @Id
    @Getter @Setter
    @Column(name = "id", unique = true ,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter @Setter
    @Column(name = "statement")
    private String statement;

    @Getter @Setter
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<AttachmentEntity> attachements = new ArrayList<>();

    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "questions_exams",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<ExamEntity> examens = new ArrayList<>();

}
