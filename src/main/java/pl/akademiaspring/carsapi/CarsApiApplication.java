package pl.akademiaspring.carsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.akademiaspring.carsapi.model.Car;
import pl.akademiaspring.carsapi.repository.CarRepo;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@CrossOrigin
public class CarsApiApplication {

    private static CarRepo carRepo;

    @Autowired
    public CarsApiApplication(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(CarsApiApplication.class, args);

        carRepo.save(new Car("Audi", "Q7", "silver"));
        carRepo.save(new Car("BMW", "i8", "silver"));
        carRepo.save(new Car("Volvo", "V60", "silver"));
    }

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
