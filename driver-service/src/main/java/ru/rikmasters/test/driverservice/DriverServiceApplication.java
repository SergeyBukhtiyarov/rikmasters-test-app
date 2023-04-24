package ru.rikmasters.test.driverservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import ru.rikmasters.test.driverservice.entity.Driver;
import ru.rikmasters.test.driverservice.repository.DriverRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class DriverServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriverServiceApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner applicationRunner(DriverRepository repository) {
//        return args -> {
//            for (int i = 0; i < 50; i++) {
//
//                var driver = new Driver();
//                driver.setBirthdate(Date.valueOf(LocalDate.now().plusDays(i)));
//                repository.save(driver);
//                repository.save(driver);
//                repository.save(driver);
//                repository.save(driver);
//            }
//
//            LocalDateTime today = LocalDate.now().atStartOfDay();
//            Date date = Date.valueOf(LocalDate.now());
//            System.out.println(today);
//            System.out.println(date);
//
//            var test = repository.findByBirthdate(date);
//            test.forEach(System.out::println);
//        };
//    }
}
