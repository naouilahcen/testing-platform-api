package ma.valueit.testingplatform.model.result;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.result.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "remarque")
public class NoteEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String description;


   /* @OneToOne(mappedBy = "remarque_id")
    private List<ResponseEntity>reponses;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reponse_id", referencedColumnName = "id")
    private ResponseEntity reponse;


}
