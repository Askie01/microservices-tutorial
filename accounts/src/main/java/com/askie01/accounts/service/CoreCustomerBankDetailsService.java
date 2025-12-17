package com.askie01.accounts.service;

import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.CardDTO;
import com.askie01.accounts.dto.CustomerBankDetailsDTO;
import com.askie01.accounts.dto.LoanDTO;
import com.askie01.accounts.mapper.AccountMapper;
import com.askie01.accounts.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreCustomerBankDetailsService implements CustomerBankDetailsService {

    private final AccountService accountService;
    private final CardService cardService;
    private final LoanService loanService;

    @Override
    @SneakyThrows
    public CustomerBankDetailsDTO getCustomerBankDetails(String mobileNumber) {
        final AccountDTO accountDTO = getAccountDTO(mobileNumber);
        final CardDTO cardDTO = cardService.getCard(mobileNumber);
        final LoanDTO loanDTO = loanService.getLoan(mobileNumber);
        return CustomerBankDetailsDTO.builder()
                .account(accountDTO)
                .card(cardDTO)
                .loan(loanDTO)
                .build();
    }

    private AccountDTO getAccountDTO(String mobileNumber) {
        final Account account = accountService.getAccount(mobileNumber);
        return AccountMapper.mapToAccountDTO(account);
    }
}
