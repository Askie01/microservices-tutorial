package com.askie01.accounts.controller;

import com.askie01.accounts.dto.CustomerBankDetailsDTO;
import com.askie01.accounts.service.CustomerBankDetailsService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerBankDetailsController {

    private final CustomerBankDetailsService customerBankDetailsService;

    @GetMapping
    public ResponseEntity<CustomerBankDetailsDTO> getCustomerBankDetails(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                                         @RequestParam String mobileNumber) {
        final CustomerBankDetailsDTO customerBankDetails = customerBankDetailsService.getCustomerBankDetails(mobileNumber);
        return new ResponseEntity<>(customerBankDetails, HttpStatus.OK);
    }
}
