package com.sid.accountservice.web;


import com.sid.accountservice.clients.CustomerRestClientOpenFeign;
import com.sid.accountservice.entities.Account;
import com.sid.accountservice.models.Customer;
import com.sid.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClientOpenFeign restClientOpenFeign;

    public BankAccountController(BankAccountRepository bankAccountRepository, CustomerRestClientOpenFeign restClientOpenFeign) {
        this.bankAccountRepository = bankAccountRepository;
        this.restClientOpenFeign = restClientOpenFeign;
    }


    @GetMapping("/accounts")
    public List<Account> accountList() {
        List<Account> accountList = bankAccountRepository.findAll();
       accountList.forEach(acc->{
           acc.setCustomer(restClientOpenFeign.findCustomerById(acc.getCustomerId()));
       });

       return  accountList;
    }

    @GetMapping("/accounts/{id}")
    public Account accountById(@PathVariable String id) {
        Account bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = restClientOpenFeign.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
