package ma.valueit.testingplatform.core.manger.mapper.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by yelansari on 1/28/18.
 */
public class JwtRequestDto implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    @NotNull(message = "login.username-is-missing")
    private String username;

    @NotNull(message = "login.password-is-missing")
    private String password;

    public JwtRequestDto() {
        super();
    }

    public JwtRequestDto(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}