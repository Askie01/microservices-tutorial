package com.askie01.accounts.repository;

import com.askie01.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerMobileNumber(String mobileNumber);
}
