package ma.valueit.testingplatform.model.exam;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "examen_nature")
public class ExamNatureEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String libelle;

    /*@OneToMany (mappedBy = "examenNature_id")
    private List<ThemeEntity> themes;*/

    @OneToMany(
            mappedBy = "examenNature",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExamEntity> examens = new ArrayList<>();

    @OneToMany(
            mappedBy = "examenNature",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ThemeEntity> themes = new ArrayList<>();


}
