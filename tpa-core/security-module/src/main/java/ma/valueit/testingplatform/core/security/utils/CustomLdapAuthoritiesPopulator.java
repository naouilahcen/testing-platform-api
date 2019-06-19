package ma.valueit.testingplatform.core.security.utils;

import ma.valueit.testingplatform.core.model.entity.user.UserEntity;
import ma.valueit.testingplatform.core.security.service.UserAuthorityService;
import ma.valueit.testingplatform.core.service.profile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by yelansari on 1/28/18.
 */
@Component
public class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {
    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthorityService userAuthorityService;

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
        UserEntity userEntity = userService.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("No account found with username '%s'.", username));
        }

        Collection<? extends GrantedAuthority> authorities = userAuthorityService.getGrantedAuthorities(username);

        return authorities;
    }
}