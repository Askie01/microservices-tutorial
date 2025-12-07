package com.askie01.messages.configuration;

import com.askie01.messages.dto.AccountsMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class MessageFunctionConfiguration {

    @Bean
    public Function<AccountsMessageDTO, AccountsMessageDTO> email() {
        return message -> {
            log.atInfo().log("Sending email with the details: " + message);
            return message;
        };
    }

    @Bean
    public Function<AccountsMessageDTO, Long> sms() {
        return message -> {
            log.atInfo().log("Sending sms with the details: " + message);
            return message.getAccountNumber();
        };
    }
}
