package com.askie01.loans.controller;

import com.askie01.loans.constants.ResponseCode;
import com.askie01.loans.constants.ResponseMessage;
import com.askie01.loans.dto.LoanDTO;
import com.askie01.loans.dto.ResponseDTO;
import com.askie01.loans.entity.Loan;
import com.askie01.loans.mapper.LoanMapper;
import com.askie01.loans.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits.")
                                                  @RequestParam String mobileNumber) {
        log.atInfo().log("Received `createLoan` POST request");
        loanService.createLoan(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.CREATED)
                .message(ResponseMessage.CREATED)
                .build();
        log.atInfo().log("Completed `createLoan` POST request");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LoanDTO> getLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                           @RequestParam String mobileNumber) {
        log.atInfo().log("Received `getLoan` GET request");
        final Loan loan = loanService.getLoan(mobileNumber);
        final LoanDTO loanDTO = LoanMapper.mapToLoanDTO(loan);
        log.atInfo().log("Completed `getLoan` GET request");
        return new ResponseEntity<>(loanDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoanDTO loanDTO) {
        log.atInfo().log("Received `updateLoan` PUT request");
        loanService.updateLoan(loanDTO);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        log.atInfo().log("Completed `updateLoan` PUT request");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteLoan(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                  @RequestParam String mobileNumber) {
        log.atInfo().log("Received `deleteLoan` DELETE request");
        loanService.deleteLoan(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        log.atInfo().log("Completed `deleteLoan` DELETE request");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
