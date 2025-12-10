package com.askie01.accounts.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class AccountsFunctions {

    //TODO: Refactor it to serve some business logic on specific events received.
    @Bean
    public Consumer<Long> updateCommunication() {
        return accountNumber -> log.atInfo().log("Updating communication status for account: " + accountNumber);
    }
}
