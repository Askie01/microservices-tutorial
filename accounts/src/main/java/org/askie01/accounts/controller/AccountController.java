package org.askie01.accounts.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.askie01.accounts.constant.ResponseCode;
import org.askie01.accounts.constant.ResponseMessage;
import org.askie01.accounts.dto.AccountContactInfoDTO;
import org.askie01.accounts.dto.AccountDTO;
import org.askie01.accounts.dto.ResponseDTO;
import org.askie01.accounts.entity.Account;
import org.askie01.accounts.mapper.AccountMapper;
import org.askie01.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountService accountService;

    @Value("${build.version}")
    private String buildVersion;
    private final Environment environment;
    private final AccountContactInfoDTO accountContactInfoDto;

    @PostMapping
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody final AccountDTO accountDTO) {
        accountService.createAccount(accountDTO);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.CREATED)
                .message(ResponseMessage.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AccountDTO> getAccount(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                 @RequestParam String mobileNumber) {
        final Account account = accountService.getAccount(mobileNumber);
        final AccountDTO accountDTO = AccountMapper.mapToAccountDTO(account);
        return new ResponseEntity<>(accountDTO, HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO) {
        accountService.updateAccount(accountDTO);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteAccount(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                     @RequestParam String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return new ResponseEntity<>(buildVersion, HttpStatus.OK);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        final String javaVersion = environment.getProperty("JAVA_HOME");
        return new ResponseEntity<>(javaVersion, HttpStatus.OK);
    }

    @GetMapping("/maven-version")
    public ResponseEntity<String> getMavenVersion() {
        final String mavenVersion = environment.getProperty("MAVEN_HOME");
        return new ResponseEntity<>(mavenVersion, HttpStatus.OK);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDTO> getContactInfo() {
        return new ResponseEntity<>(accountContactInfoDto, HttpStatus.OK);
    }
}
