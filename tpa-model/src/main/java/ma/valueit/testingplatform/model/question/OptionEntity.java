package ma.valueit.testingplatform.model.question;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.result.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "options")
public class OptionEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String reponse;

    @Getter
    @Setter
    private boolean correct;

   /* @ManyToOne
    @JoinColumn(name = "id")
    private ResponseEntity reponseEntity;*/

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "response_id")
    private ResponseEntity response;


}
