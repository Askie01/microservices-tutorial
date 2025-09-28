package org.askie01.accounts.service;

import org.askie01.accounts.dto.AccountDTO;
import org.askie01.accounts.entity.Account;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);

    Account getAccount(String mobileNumber);

    Account updateAccount(AccountDTO accountDTO);

    Account deleteAccount(String mobileNumber);
}
