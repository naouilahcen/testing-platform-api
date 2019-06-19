package ma.valueit.testingplatform.rest;

import ma.valueit.testingplatform.core.errorhandling.businessexception.ResponseBody;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;
import ma.valueit.testingplatform.core.manger.mapper.dto.JwtRequestDto;
import ma.valueit.testingplatform.core.manger.mapper.dto.JwtResponseDto;
import ma.valueit.testingplatform.core.manger.user.UserManager;
import ma.valueit.testingplatform.core.manger.user.mapper.dto.UserDto;
import ma.valueit.testingplatform.core.security.model.JwtUser;
import ma.valueit.testingplatform.core.security.service.JdbcUserDetailsService;
import ma.valueit.testingplatform.core.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Context;

/**
 * Created by yelansari on 1/28/18.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationActivity {

    @Autowired
    protected UserManager userManager;

    @Autowired
    protected JdbcUserDetailsService userDetailsService;

    @Value("${authentication.jwt.expiration}")
    protected Long expiresIn;

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<ResponseBody> createAuthenticationToken(@RequestBody @Valid JwtRequestDto authenticationRequest, @Context HttpServletRequest httpRequest) {

        // Perform the security
        Authentication authentication;

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new ResponseBody<>(UserErrorCode.USERNAME_OR_PASSWORD_IS_INCORRECT), HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final JwtUser userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        UserDto userDetailsDto = userManager.findByUsername(authenticationRequest.getUsername());

        ResponseBody<JwtResponseDto> responseBody = new ResponseBody<>(new JwtResponseDto<>(token, userDetailsDto, expiresIn));

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public ResponseEntity<ResponseBody> refreshAndGetAuthenticationToken(@Context HttpServletRequest httpRequest) {

        String authToken = jwtTokenUtil.getTokenFromRequest(httpRequest);

        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        ResponseBody responseBody;

        if (username == null) {
            responseBody = new ResponseBody(HttpStatus.BAD_REQUEST);
        } else {
            JwtUser user = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.canTokenBeRefreshed(authToken, user.getLastPasswordResetDate())) {
                String refreshedToken = jwtTokenUtil.refreshToken(authToken);

                responseBody = new ResponseBody<JwtResponseDto>(new JwtResponseDto(refreshedToken));
            } else {
                responseBody = new ResponseBody(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
