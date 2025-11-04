package com.askie01.accounts.controller;

import com.askie01.accounts.dto.ContactInformationDTO;
import com.askie01.accounts.service.InformationService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
@RequiredArgsConstructor
public class InformationController {

    private final InformationService informationService;

    @Retry(name = "getBuildVersion", fallbackMethod = "getBuildVersionFallback")
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        final String buildVersion = informationService.getBuildVersion();
        throw new NullPointerException("Test exception");
//        return new ResponseEntity<>(buildVersion, HttpStatus.OK);
    }

    public ResponseEntity<String> getBuildVersionFallback(Throwable throwable) {
        System.out.println("The fallback has been called.");
        return ResponseEntity.status(HttpStatus.OK).body("0.9");
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        final String javaVersion = informationService.getJavaVersion();
        return new ResponseEntity<>(javaVersion, HttpStatus.OK);
    }

    @GetMapping("/maven-version")
    public ResponseEntity<String> getMavenVersion() {
        final String mavenVersion = informationService.getMavenVersion();
        return new ResponseEntity<>(mavenVersion, HttpStatus.OK);
    }

    @GetMapping("/contact-information")
    public ResponseEntity<ContactInformationDTO> getContactInfo() {
        final ContactInformationDTO contactInformation = informationService.getContactInformation();
        return new ResponseEntity<>(contactInformation, HttpStatus.OK);
    }
}
