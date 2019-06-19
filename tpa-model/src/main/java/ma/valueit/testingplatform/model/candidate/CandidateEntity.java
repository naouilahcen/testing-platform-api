package ma.valueit.testingplatform.model.candidate;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "candidat")
public class CandidateEntity extends CustomAuditable<String , Integer , LocalDate> {

    @Id
    @Getter
    @Setter
    @Column(name = "id", unique = true ,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastname;

    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstname;

    @Getter
    @Setter
    @Column(name = "mail")
    private String mail ;

    @Getter
    @Setter
    @Column(name = "years_of_expirience")
    private String yearsOfExpirience;

    @Getter
    @Setter
    @Column(name = "diploma")
    private String diploma ;

    @Getter
    @Setter
    @Column(name = "year_of_diploma")
    private String yearOfDiploma ;

    @OneToMany(
            mappedBy = "candidate",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<SessionEntity> sessions = new ArrayList<>();




}
