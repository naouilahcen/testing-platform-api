package ma.valueit.testingplatform.core.security.service;

import ma.valueit.testingplatform.core.model.entity.user.AuthTypeEnum;
import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.security.model.JwtUser;
import ma.valueit.testingplatform.core.service.profile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by yelansari on 1/28/18.
 */
@Component
public class JdbcUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthorityService userAuthorityService;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserEntity user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }

            Collection<? extends GrantedAuthority> authorities = userAuthorityService.getGrantedAuthorities(username);

            JwtUser jwtUser;

            jwtUser = new JwtUser(
                    user.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getPassword(),
                    user.getEmail(),
                    (user.getAuthType() == null ? AuthTypeEnum.SIMPLE.name() : user.getAuthType().name()),
                    user.isAdmin(),
                    authorities,
                    user.isEnabled(),
                    user.getLastPasswordResetDate()
            );

            return jwtUser;
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("No account found with username '%s'.", username));
        }
    }
}
