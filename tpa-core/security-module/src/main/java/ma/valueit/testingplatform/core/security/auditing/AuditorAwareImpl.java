package ma.valueit.testingplatform.core.security.auditing;

import ma.valueit.testingplatform.core.security.model.JwtUser;
import ma.valueit.testingplatform.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created by yelansari on 4/30/18.
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null &&!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            try {
                JwtUser user = (JwtUser) authentication.getPrincipal();

                if (user != null) {
                    return Optional.of(user.getUsername());
                }
            } catch (Exception e) {
                if(!StringUtils.isEmpty(currentUserName)){
                    return Optional.of(currentUserName);
                }

                LOGGER.error(e.getMessage(), e);
            }
        }

        return Optional.of("System");
    }
}
