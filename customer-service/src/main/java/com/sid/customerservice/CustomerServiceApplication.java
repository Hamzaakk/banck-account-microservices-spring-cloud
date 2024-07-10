package com.sid.customerservice;

import com.sid.customerservice.entities.Customer;
import com.sid.customerservice.repository.CustomerRepository;
import config.GlobalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {


            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstname("HAMZA")
                            .lastname("AkKAOUI")
                            .email("Hamzaakkaoui@gmail.com")
                            .build(),

                    Customer.builder()
                            .firstname("AMINE")
                            .lastname("SASSI")
                            .email("AMINESASSI@gmail.com")
                            .build()
            );

            customerRepository.saveAll(customerList);
        };
    }
}
