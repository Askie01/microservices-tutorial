package com.askie01.accounts.service;

import com.askie01.accounts.client.CardsApiClient;
import com.askie01.accounts.client.LoansApiClient;
import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.CardDTO;
import com.askie01.accounts.dto.CustomerBankDetailsDTO;
import com.askie01.accounts.dto.LoanDTO;
import com.askie01.accounts.entity.Account;
import com.askie01.accounts.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreCustomerBankDetailsService implements CustomerBankDetailsService {

    private final AccountService accountService;
    private final CardsApiClient cardsApiClient;
    private final LoansApiClient loansApiClient;

    @Override
    public CustomerBankDetailsDTO getCustomerBankDetails(String mobileNumber) {
        final AccountDTO accountDTO = getAccountDTO(mobileNumber);
        final CardDTO cardDTO = cardsApiClient.getCard(mobileNumber).getBody();
        final LoanDTO loanDTO = loansApiClient.getLoan(mobileNumber).getBody();
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
