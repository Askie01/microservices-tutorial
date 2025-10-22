package com.askie01.accounts.controller;

import com.askie01.accounts.constant.ResponseCode;
import com.askie01.accounts.constant.ResponseMessage;
import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.CustomerDTO;
import com.askie01.accounts.dto.ResponseDTO;
import com.askie01.accounts.entity.Account;
import com.askie01.accounts.mapper.AccountMapper;
import com.askie01.accounts.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
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
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
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
}
