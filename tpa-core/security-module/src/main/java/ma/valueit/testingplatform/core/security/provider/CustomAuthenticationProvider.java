package ma.valueit.testingplatform.core.security.provider;

import ma.valueit.testingplatform.core.errorhandling.businessexception.InvalidArgumentException;
import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.profile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by yelansari on 1/28/18.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserEntity userEntity = userService.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

        AuthTypeEnum authType = userEntity.getAuthType();

        switch (authType) {
            case SIMPLE:
                return daoAuthenticationProvider.authenticate(authentication);
            default:
                throw new InvalidArgumentException(String.format("'%s' is not yet implemented!", authType));
        }
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
