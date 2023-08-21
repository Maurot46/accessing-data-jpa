package com.example.accessingdatajpa;

import com.example.accessingdatajpa.entities.Customer;
import com.example.accessingdatajpa.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingdatajpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingdatajpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingdatajpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer("Jackye","Chong"));
            customerRepository.save(new Customer("Dead", "Pool"));
            customerRepository.save(new Customer("Artic","Man"));
            customerRepository.save(new Customer("Mantis","Red"));
            customerRepository.save(new Customer("Jack","Magic"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = customerRepository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            customerRepository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }
}
