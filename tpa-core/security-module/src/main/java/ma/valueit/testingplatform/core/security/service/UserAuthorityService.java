package ma.valueit.testingplatform.core.security.service;

import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.service.profile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by yelansari on 1/28/18.
 */
@Component
public class UserAuthorityService {
    @Autowired
    private UserService userService;

    public Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No account found with username '%s'.", username));
        }

        return user.getGrantedAuthoritiesList();
    }
}
