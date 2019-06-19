package ma.valueit.testingplatform.core.errorhandling;

import ma.valueit.testingplatform.core.errorhandling.businessexception.*;
import ma.valueit.testingplatform.core.errorhandling.exception.UserErrorCode;
import ma.valueit.testingplatform.core.errorhandling.filemanagererror.FileManagerException;
import ma.valueit.testingplatform.core.errorhandling.filemanagererror.FileManagerFileNotFoundException;
import ma.valueit.testingplatform.core.errorhandling.filemanagererror.FileManagerIOException;
import ma.valueit.testingplatform.core.errorhandling.filemanagererror.FileManagerInitializationException;
import ma.valueit.testingplatform.core.errorhandling.validator.ValidationMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by yelansari on 3/10/18.
 */
@RestControllerAdvice
public class WebApplicationExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<String>> handleBusinessException(BusinessException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseBody<String>> handleUserNotFoundException(UserNotFoundException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseBody<String>> handleUsernameNotFoundException(UsernameNotFoundException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = UserErrorCode.NO_USER_FOUND_BY_USERNAME.getValue();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ResponseBody<String>> handleUnAuthorizedException(UnAuthorizedException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseBody<String>> handleIntegrityConstraintViolationException(IntegrityConstraintViolationException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity<ResponseBody<String>> handleInvalidPayloadException(InvalidPayloadException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();


        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingIdException.class)
    public ResponseEntity<ResponseBody<String>> handleMissingIdException(MissingIdException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<>(new ResponseBody<>("", errorMessage), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdvancedBusinessException.class)
    public ResponseEntity<ResponseBody<ValidationMessages>> handleAdvancedBusinessException(AdvancedBusinessException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = ex.getErrorCode() != null ? ex.getErrorCode().getValue() : ex.getMessage();

        return new ResponseEntity<ResponseBody<ValidationMessages>>(new ResponseBody<>(ex.getValidationMessages(), ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ResponseBody<String>> InvalidArgumentException(InvalidArgumentException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ResponseBody<String>> NoContentException(NoContentException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(), headers, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseBody<String>> NotFoundException(EntityNotFoundException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(), headers, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(FileManagerInitializationException.class)
    public ResponseEntity<ResponseBody<String>> handleFMInitializationException(FileManagerInitializationException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileManagerFileNotFoundException.class)
    public ResponseEntity<ResponseBody<String>> handleFMFileNotFoundException(FileManagerFileNotFoundException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileManagerIOException.class)
    public ResponseEntity<ResponseBody<String>> handleFMIOException(FileManagerIOException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileManagerException.class)
    public ResponseEntity<ResponseBody<String>> handleFileManagerException(FileManagerException ex, @Context HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(new ResponseBody<>(ex.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

}
