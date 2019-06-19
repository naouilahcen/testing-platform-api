package ma.valueit.testingplatform.model.candidate;

import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.model.candidate.CandidateEntity;
import ma.valueit.testingplatform.model.result.ResponseEntity;
import ma.valueit.testingplatform.model.result.ResultEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "session")
public class SessionEntity extends CustomAuditable<String, Integer, LocalDate> {


    @Id
    @Getter
    @Setter
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Getter
    @Setter
    @Column(name = "token")
    private String token;

    @Getter
    @Setter
    @Column(name = "begin_date")
    private LocalDateTime beginDate;

    @Getter
    @Setter
    @Column(name = "end_date")
    private LocalDateTime dateEnd;

    @Getter
    @Setter
    @Column(name = "duration")
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @Getter @Setter
    private CandidateEntity candidate;

    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Getter @Setter
    private List<ResultEntity> results = new ArrayList<>();
}
