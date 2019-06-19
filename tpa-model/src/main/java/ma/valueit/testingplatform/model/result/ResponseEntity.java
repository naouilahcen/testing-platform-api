package ma.valueit.testingplatform.model.result;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.attachment.AttachmentEntity;
import ma.valueit.testingplatform.model.question.OptionEntity;
import ma.valueit.testingplatform.model.candidate.SessionEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "reponse")
public class ResponseEntity extends CustomAuditable<String , Integer , LocalDate> {


    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    private String reponse;



    /*@OneToOne(mappedBy = "reponse_id")
    private List<NoteEntity>remarques;

    @OneToMany (mappedBy = "reponse_id")
    private List<OptionEntity>options;

    @OneToMany (mappedBy = "reponse_id")
    private List<AttachmentEntity>attachements;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reponse_id", referencedColumnName = "id")
    private SessionEntity session;

    @OneToOne (mappedBy = "reponse")
    private NoteEntity remarque;

/*    @OneToMany(
            mappedBy = "reponse",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AttachmentEntity> attachements = new ArrayList<>();
*/
    @OneToMany(
            mappedBy = "response",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OptionEntity> options = new ArrayList<>();




}