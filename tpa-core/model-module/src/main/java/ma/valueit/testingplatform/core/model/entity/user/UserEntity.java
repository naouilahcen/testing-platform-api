package ma.valueit.testingplatform.core.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ma.valueit.testingplatform.core.model.entity.auditing.CustomAuditable;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by yelansari on 1/28/18.
 */
@Entity
@EntityListeners({UserEntityListener.class})
@Table(name = "users")
public class UserEntity extends CustomAuditable<String, Integer, LocalDate> {
    private static final long serialVersionUID = 6185248802496408283L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Column(unique = true)
    @Getter
    @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String mobile;

    @Getter @Setter
    private String address;

    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    @Getter @Setter
    private Byte[] avatar;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private boolean admin = false;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private AuthTypeEnum authType;

    @Column(length = 3000)
    @Getter @Setter
    private String resetToken;

    @Column(length = 8)
    @Getter @Setter
    private Integer resetCode;

    @Getter
    @Setter
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    @Getter @Setter
    private ProfileEntity profile;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date lastPasswordResetDate;

    public String getFullName() {
        return this.getFirstname() + " " + this.getLastname();
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "excluded_authorities",
            joinColumns = @JoinColumn(
                    name = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id"
            )
    )
    @Getter @Setter
    private Set<AuthorityEntity> excludedAuthorities = new LinkedHashSet<>();


    public List<AuthorityEntity> getGrantedAuthorities() {
        if(this.profile == null || CollectionUtils.isEmpty(this.profile.getAuthorities())) return null;

        List<AuthorityEntity> authorities = new ArrayList<>(profile.getAuthorities());

        if(!CollectionUtils.isEmpty(excludedAuthorities)) {
            authorities.removeAll(excludedAuthorities);
        }

        return authorities;
    }

    public boolean hasAuthority(AuthorityCategoryEntity authority) {
        List<AuthorityEntity> authorities = getGrantedAuthorities();

        if(CollectionUtils.isEmpty(authorities)) return false;

        for(AuthorityEntity authorityEntity : authorities){
            if(authorityEntity.getCategory().equals(authority)){
                return true;
            }
        }

        return false;
    }

    public List<? extends GrantedAuthority> getGrantedAuthoritiesList() {
        List<AuthorityEntity> grantedAuthorities = getGrantedAuthorities();

        if(CollectionUtils.isEmpty(grantedAuthorities)) {
            return Collections.<GrantedAuthority> emptyList();
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

        grantedAuthorities.forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority.getCode()));
        });

        return authorities;
    }

}
