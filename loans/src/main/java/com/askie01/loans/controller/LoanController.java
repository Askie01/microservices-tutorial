package com.askie01.loans.controller;

import com.askie01.loans.constants.ResponseCode;
import com.askie01.loans.constants.ResponseMessage;
import com.askie01.loans.dto.LoanContactInfoDTO;
import com.askie01.loans.dto.LoanDTO;
import com.askie01.loans.dto.ResponseDTO;
import com.askie01.loans.entity.Loan;
import com.askie01.loans.mapper.LoanMapper;
import com.askie01.loans.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/loans")
public class LoanController {
    private final LoanService loanService;

    @Value("${build.version}")
    private String buildVersion;
    private final Environment environment;
    private final LoanContactInfoDTO loanContactInfoDTO;

    @PostMapping
    public ResponseEntity<ResponseDTO> createLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits.")
                                                  @RequestParam String mobileNumber) {
        loanService.createLoan(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.CREATED)
                .message(ResponseMessage.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LoanDTO> getLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                           @RequestParam String mobileNumber) {
        final Loan loan = loanService.getLoan(mobileNumber);
        final LoanDTO loanDTO = LoanMapper.mapToLoanDTO(loan);
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoanDTO loanDTO) {
        loanService.updateLoan(loanDTO);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                  @RequestParam String mobileNumber) {
        loanService.deleteLoan(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return new ResponseEntity<>(buildVersion, HttpStatus.OK);
    }

    @GetMapping(path = "/java-version")
    public ResponseEntity<String> getJavaVersion() {
        final String javaVersion = environment.getProperty("JAVA_HOME");
        return new ResponseEntity<>(javaVersion, HttpStatus.OK);
    }

    @GetMapping(path = "/maven-version")
    public ResponseEntity<String> getMavenVersion() {
        final String mavenVersion = environment.getProperty("MAVEN_HOME");
        return new ResponseEntity<>(mavenVersion, HttpStatus.OK);
    }

    @GetMapping(path = "/contact-info")
    public ResponseEntity<LoanContactInfoDTO> getContactInfo() {
        return new ResponseEntity<>(loanContactInfoDTO, HttpStatus.OK);
    }
}
