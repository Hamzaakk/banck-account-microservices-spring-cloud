package com.sid.accountservice;

import com.sid.accountservice.entities.Account;
import com.sid.accountservice.enums.AccountType;
import com.sid.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args ->  {


            List<Account> accountsList = List.of(
                    Account.builder().accountId(UUID.randomUUID().toString())
                            .currency("MAD")
                            .balance(98383)
                            .createdDate(LocalDate.now())
                            .type(AccountType.CURRENT_ACCOUNT)
                            .customerId(Long.valueOf(1))
                            .build(),


                    Account.builder().accountId(UUID.randomUUID().toString())
                            .currency("MAD")
                            .balance(22334)
                            .createdDate(LocalDate.now())
                            .type(AccountType.CURRENT_ACCOUNT)
                            .customerId(Long.valueOf(2))
                            .build()
            );

            bankAccountRepository.saveAll(accountsList);

        };
    }

}
