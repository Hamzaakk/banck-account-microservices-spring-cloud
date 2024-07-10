package com.sid.accountservice.clients;

import com.sid.accountservice.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClientOpenFeign {
    @CircuitBreaker(name = "customerService" ,fallbackMethod = "getDefaultAllCustomers")
    @GetMapping("/customers")
    List<Customer> allCustomers();

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService" ,fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable  Long id);
    default List<Customer> getDefaultAllCustomers() {
        return List.of();
    }
    default Customer getDefaultCustomer(Long id ,Exception ex) {
        Customer  customer = new Customer();
        customer.setId(id);
        customer.setFirstname("DEF NAME");
        customer.setLastname("DEF LASTNAME");
        customer.setEmail("DEF EMAIL");

        return customer;
    }

}
