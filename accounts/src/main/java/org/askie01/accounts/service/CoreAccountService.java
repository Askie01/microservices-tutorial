package org.askie01.accounts.service;

import lombok.RequiredArgsConstructor;
import org.askie01.accounts.constant.AccountType;
import org.askie01.accounts.constant.BranchAddress;
import org.askie01.accounts.dto.AccountDTO;
import org.askie01.accounts.dto.CustomerDTO;
import org.askie01.accounts.entity.Account;
import org.askie01.accounts.entity.Customer;
import org.askie01.accounts.exception.AccountNotFoundException;
import org.askie01.accounts.exception.CustomerNotFoundException;
import org.askie01.accounts.exception.MobilePhoneAlreadyExistsException;
import org.askie01.accounts.mapper.AccountMapper;
import org.askie01.accounts.mapper.CustomerMapper;
import org.askie01.accounts.repositories.AccountRepository;
import org.askie01.accounts.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoreAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        final CustomerDTO customerDTO = accountDTO.getCustomerDTO();
        final String mobileNumber = customerDTO.getMobileNumber();
        final boolean customerExists = customerRepository
                .findByMobileNumber(mobileNumber)
                .isPresent();
        if (customerExists) {
            throw new MobilePhoneAlreadyExistsException(mobileNumber);
        }
        final Customer customer = CustomerMapper.mapToCustomer(customerDTO);
        return createDefaultAccount(customer);
    }

    private Account createDefaultAccount(Customer customer) {
        final Account account = Account.builder()
                .type(AccountType.SAVINGS)
                .branchAddress(BranchAddress.MAIN_STREET_NEW_YORK)
                .customer(customer)
                .build();
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String mobileNumber) {
        return accountRepository
                .findByCustomerMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotFoundException(mobileNumber));
    }

    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        final Long id = accountDTO.getId();
        final Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        AccountMapper.map(accountDTO, account);
        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccount(String mobileNumber) {
        return accountRepository
                .deleteByCustomerMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotFoundException(mobileNumber));
    }
}
