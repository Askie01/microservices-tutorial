package com.askie01.accounts.service;

import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.CustomerDTO;
import com.askie01.accounts.entity.Account;

public interface AccountService {
    Account createAccount(CustomerDTO customerDTO);

    Account getAccount(String mobileNumber);

    Account updateAccount(AccountDTO accountDTO);

    Account deleteAccount(String mobileNumber);
}
