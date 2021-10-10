package br.com.itau.unittestingworkshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UnitTestingWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestingWorkshopApplication.class, args);
    }

}
