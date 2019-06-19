package ma.valueit.testingplatform.core.errorhandling.businessexception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BusinessExceptionMapper {
    public ResponseEntity<BusinessErrorCode> toResponse(BusinessException ex) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(ex.getErrorCode(), headers, ex.getStatus());
    }
}
