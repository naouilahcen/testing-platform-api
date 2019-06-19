package ma.valueit.testingplatform.model.exam;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.exam.ExamNatureEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "theme")
public class ThemeEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String libelle;

    /*@ManyToOne
    @JoinColumn(name = "id")
    private ExamNatureEntity examenNatureEntity;*/

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "examenNature_id")
    private ExamNatureEntity examenNature;
}