package com.askie01.loans.repository;

import com.askie01.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);

    Optional<Loan> findByNumber(Long loanNumber);

    Optional<Loan> deleteByMobileNumber(String mobileNumber);
}
