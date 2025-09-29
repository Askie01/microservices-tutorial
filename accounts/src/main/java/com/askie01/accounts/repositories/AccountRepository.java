package com.askie01.accounts.repositories;

import com.askie01.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerMobileNumber(String mobileNumber);
}
