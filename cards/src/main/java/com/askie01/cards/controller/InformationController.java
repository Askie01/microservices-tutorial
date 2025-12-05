package com.askie01.cards.controller;

import com.askie01.cards.dto.ContactInformationDTO;
import com.askie01.cards.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/information")
@RequiredArgsConstructor
public class InformationController {

    private final InformationService informationService;

    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        final String buildVersion = informationService.getBuildVersion();
        return new ResponseEntity<>(buildVersion, HttpStatus.OK);
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