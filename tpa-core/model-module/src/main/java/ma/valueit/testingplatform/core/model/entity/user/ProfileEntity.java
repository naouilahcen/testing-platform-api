package ma.valueit.testingplatform.core.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by yelansari on 2/22/18.
 */
@Entity
@EntityListeners(ProfileEntityListener.class)
@Table(name = "profiles")
@ToString(exclude = {
        "authorities"
})
@Data
public class ProfileEntity extends CustomAuditable<String, Integer, LocalDate> implements Serializable {
    private static final long serialVersionUID = 167338507135474088L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "profiles_authorities",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    @JsonIgnore
    @Getter @Setter
    private Set<AuthorityEntity> authorities = new LinkedHashSet<>();
}
