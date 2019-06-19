package ma.valueit.testingplatform.core.model.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by yelansari on 2/22/18.
 */
@Entity
@Table(name = "authorities")
@ToString
public class AuthorityEntity extends CustomAuditable<String, Integer, LocalDate> implements Serializable{
    private static final long serialVersionUID = -4414534673832359181L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String code;

    @ManyToOne
    @Getter @Setter
    private AuthorityCategoryEntity category;
}
