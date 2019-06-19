package ma.valueit.testingplatform.core.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by yelansari on 1/28/18.
 */
@ToString(exclude = {
        "email",
        "password",
        "authType",
        "authorities",
        "enabled",
        "admin",
        "lastPasswordResetDate"
})
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = -2379230455682652857L;

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @JsonIgnore
    @Getter
    @Setter
    private String email;

    @JsonIgnore
    @Getter
    @Setter
    private String password;

    @JsonIgnore
    @Getter
    @Setter
    private String authType;

    @JsonIgnore
    @Getter
    @Setter
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @Getter
    @Setter
    private boolean enabled;

    @JsonIgnore
    @Getter
    @Setter
    private boolean admin;

    @JsonIgnore
    @Getter
    @Setter
    private Date lastPasswordResetDate;

    public JwtUser() {
        super();
    }

    public JwtUser(Integer id, String username, String firstname, String lastname, String password, String email, String authType, boolean admin, Collection<? extends GrantedAuthority> authorities, boolean enabled, Date lastPasswordResetDate) {
        super();

        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.authType = authType;
        this.authorities = authorities;
        this.admin = admin;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
