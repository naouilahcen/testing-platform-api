package ma.valueit.testingplatform.core.manger.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * Created by yelansari on 1/28/18.
 */
public class JwtResponseDto<T> implements Serializable {
    private static final long serialVersionUID = -2530245949703041167L;

    @Getter
    @Setter
    private String accessToken;

    @Getter
    @Setter
    private String tokenType = "Bearer";

    @Getter
    @Setter
    private boolean refreshToken = false;

    @Value("${authentication.jwt.expiration}")
    @Getter
    @Setter
    private Long expiresIn;

    @Getter
    @Setter
    private String scope = null;

    @Getter
    @Setter
    private T user = null;

    public JwtResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public JwtResponseDto(String accessToken, T user) {
        this.accessToken = accessToken;

        this.user = user;
    }

    public JwtResponseDto(String accessToken, T user, Long expiresIn) {
        this.accessToken = accessToken;

        this.user = user;

        this.expiresIn = expiresIn;
    }

    public JwtResponseDto(String accessToken, boolean refreshToken, Long expiresIn, String scope, T user) {
        this.accessToken = accessToken;

        this.refreshToken = refreshToken;

        this.expiresIn = expiresIn;

        this.scope = scope;

        this.user = user;
    }

    public JwtResponseDto(String accessToken, String tokenType, boolean refreshToken, Long expiresIn, String scope, T user) {
        this.accessToken = accessToken;

        this.tokenType = tokenType;

        this.refreshToken = refreshToken;

        this.expiresIn = expiresIn;

        this.scope = scope;

        this.user = user;
    }
}
