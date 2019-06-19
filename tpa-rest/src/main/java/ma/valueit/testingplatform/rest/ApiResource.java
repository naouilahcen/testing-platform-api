package ma.valueit.testingplatform.rest;

import ma.valueit.testingplatform.core.errorhandling.businessexception.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by yelansari on 1/29/18.
 */
@RestController
public class ApiResource {

    @Value("${api.version}")
    private String apiVersion;

    @GetMapping
    public ResponseEntity<ResponseBody<String>> home(@Context HttpServletRequest httpRequest) {

        return new ResponseEntity<>(new ResponseBody<>("Testing Platform API Version " + apiVersion), HttpStatus.OK);

    }

    @GetMapping("/about")
    public ResponseEntity<ResponseBody<String>> about(@Context HttpServletRequest httpRequest) {
        return new ResponseEntity<>(new ResponseBody<>("Testing Platform API Version " + apiVersion), HttpStatus.OK);

    }
}