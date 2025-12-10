package com.askie01.accounts.service;

import com.askie01.accounts.constant.AccountType;
import com.askie01.accounts.constant.BranchAddress;
import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.AccountsMessageDTO;
import com.askie01.accounts.dto.CustomerDTO;
import com.askie01.accounts.entity.Account;
import com.askie01.accounts.entity.Customer;
import com.askie01.accounts.exception.CustomerNotFoundException;
import com.askie01.accounts.exception.MobilePhoneAlreadyExistsException;
import com.askie01.accounts.mapper.AccountMapper;
import com.askie01.accounts.mapper.CustomerMapper;
import com.askie01.accounts.repositories.AccountRepository;
import com.askie01.accounts.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreAccountService implements AccountService {

    private final StreamBridge streamBridge;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Account createAccount(CustomerDTO customerDTO) {
        final String mobileNumber = customerDTO.getMobileNumber();
        final boolean customerExists = customerRepository
                .findByMobileNumber(mobileNumber)
                .isPresent();
        if (customerExists) {
            throw new MobilePhoneAlreadyExistsException(mobileNumber);
        }
        final Customer customer = CustomerMapper.mapToCustomer(customerDTO);
        final Account newAccount = createDefaultAccount(customer);
        sendCommunication(newAccount);
        return newAccount;
    }

    private Account createDefaultAccount(Customer customer) {
        final Account account = Account.builder()
                .type(AccountType.SAVINGS)
                .branchAddress(BranchAddress.MAIN_STREET_NEW_YORK)
                .customer(customer)
                .build();
        return accountRepository.save(account);
    }

    //TODO: This method is outside of this service scope - move it to the communication-related service.
    private void sendCommunication(Account account) {
        final Long accountNumber = account.getId();
        final String name = account.getCustomer().getName();
        final String email = account.getCustomer().getEmail();
        final String mobileNumber = account.getCustomer().getMobileNumber();
        final AccountsMessageDTO message = AccountsMessageDTO.builder()
                .accountNumber(accountNumber)
                .name(name)
                .email(email)
                .mobileNumber(mobileNumber)
                .build();
        log.atInfo().log("Sending communication request for the details: {}", message);
        final boolean result = streamBridge.send("sendCommunication-out-0", message);
        log.atInfo().log("Is the communication request successfully processed? : {}", result);
    }

    @Override
    public Account getAccount(String mobileNumber) {
        return accountRepository
                .findByCustomerMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotFoundException(mobileNumber));
    }

    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        final String customerMobileNumber = accountDTO.getCustomerDTO().getMobileNumber();
        final Account account = getAccount(customerMobileNumber);
        AccountMapper.map(accountDTO, account);
        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccount(String mobileNumber) {
        final Account account = getAccount(mobileNumber);
        accountRepository.delete(account);
        return account;
    }
}
