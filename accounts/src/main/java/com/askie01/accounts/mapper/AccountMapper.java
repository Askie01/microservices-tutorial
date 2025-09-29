package com.askie01.accounts.mapper;

import com.askie01.accounts.dto.AccountDTO;
import com.askie01.accounts.dto.CustomerDTO;
import com.askie01.accounts.entity.Account;
import com.askie01.accounts.entity.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account) {
        final AccountDTO accountDTO = AccountDTO.builder()
                .customerDTO(new CustomerDTO())
                .build();
        map(account, accountDTO);
        return accountDTO;
    }

    public static void map(Account source, AccountDTO target) {
        target.setId(source.getId());
        target.setType(source.getType());
        target.setBranchAddress(source.getBranchAddress());

        final Customer customer = source.getCustomer();
        final CustomerDTO customerDTO = target.getCustomerDTO();
        CustomerMapper.map(customer, customerDTO);
    }

    public static Account mapToAccount(AccountDTO accountDTO) {
        final Account account = Account.builder()
                .customer(new Customer())
                .build();
        map(accountDTO, account);
        return account;
    }

    public static void map(AccountDTO source, Account target) {
        target.setId(source.getId());
        target.setType(source.getType());
        target.setBranchAddress(source.getBranchAddress());

        final CustomerDTO customerDTO = source.getCustomerDTO();
        final Customer customer = target.getCustomer();
        CustomerMapper.map(customerDTO, customer);
    }
}
