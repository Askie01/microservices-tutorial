package com.askie01.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/contact-support")
    public Mono<String> contactSupport() {
        final String message = "Error occurred, please contact support";
        return Mono.just(message);
    }
}
