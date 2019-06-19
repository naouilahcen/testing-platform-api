package ma.valueit.testingplatform.core.security.config;

import ma.valueit.testingplatform.core.security.provider.CustomAuthenticationProvider;
import ma.valueit.testingplatform.core.security.service.JwtAuthenticationTokenFilter;
import ma.valueit.testingplatform.core.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${security.allowed.posts}")
    private String[] allowedPosts;

    @Value("${security.allowed.gets}")
    private String[] allowedGets;

    @Value("${security.allowed.puts}")
    private String[] allowedPuts;

    @Value("${security.allowed.deletes}")
    private String[] allowedDeletes;

    @Value("${security.cors.allowedHeaders}")
    private String[] allowedHeaders;

    @Value("${security.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Value("${security.cors.allowedMethods}")
    private String[] allowedMethods;

    @Value("${security.cors.allowCredentials}")
    private boolean allowCredentials;

    @Value("${security.cors.maxAge}")
    private Long maxAge;

    @Value("${security.cors.configurationPath}")
    private String corsConfigurationPath;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    @Qualifier("jwtAuthenticationEntryPoint")
    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    @Qualifier("corsConfigurationSource")
    private CorsConfigurationSource corsConfigurationSource;

    @Bean(name = "jwtAuthenticationTokenFilter")
    public UsernamePasswordAuthenticationFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource)
            .and()
            .csrf().disable()
            .authenticationProvider(customAuthenticationProvider)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, allowedPosts).permitAll()
            .antMatchers(HttpMethod.GET, allowedGets).permitAll()
            .antMatchers(HttpMethod.PUT, allowedPuts).permitAll()
            .antMatchers(HttpMethod.DELETE, allowedDeletes).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationTokenFilter(), JwtAuthenticationTokenFilter.class)
        ;
    }

    @Bean(name = "corsConfigurationSource")
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        if(!CollectionUtils.isEmpty(allowedOrigins)) {
            for (String origin : allowedOrigins) {
                config.addAllowedOrigin(origin);
            }
        }

        if(!CollectionUtils.isEmpty(allowedHeaders)) {
            for (String header : allowedHeaders) {
                config.addAllowedHeader(header);
            }
        }

        config.setAllowedMethods(Arrays.asList(allowedMethods));

        config.setAllowCredentials(true);

        config.setMaxAge(maxAge);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(customAuthenticationProvider);
        return new ProviderManager(authenticationProviders);
    }
}
