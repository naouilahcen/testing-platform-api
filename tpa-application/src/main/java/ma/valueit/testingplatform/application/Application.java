package ma.valueit.testingplatform.application;

import ma.valueit.testingplatform.model.product.ProductEntity;
import ma.valueit.testingplatform.repository.test.ProductRepository;
import ma.valueit.testingplatform.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan("ma.valueit.testingplatform")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello ValueIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Hello ValueIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Hello ValueIT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");



    }

}
