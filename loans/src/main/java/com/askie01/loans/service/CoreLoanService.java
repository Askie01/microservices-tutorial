package com.askie01.loans.service;

import com.askie01.loans.constants.LoanAmount;
import com.askie01.loans.constants.LoanType;
import com.askie01.loans.dto.LoanDTO;
import com.askie01.loans.entity.Loan;
import com.askie01.loans.exception.LoanAlreadyExistsException;
import com.askie01.loans.exception.LoanNotFoundException;
import com.askie01.loans.mapper.LoanMapper;
import com.askie01.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CoreLoanService implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public Loan createLoan(String mobileNumber) {
        final boolean loanExists = loanRepository
                .findByMobileNumber(mobileNumber)
                .isPresent();
        if (loanExists) {
            throw new LoanAlreadyExistsException(mobileNumber);
        }
        return createDefaultLoan(mobileNumber);
    }

    private Loan createDefaultLoan(String mobileNumber) {
        final long randomLoanNumber = 100_000_000_000L
                + ThreadLocalRandom.current().nextInt(900_000_000);
        final Loan loan = Loan.builder()
                .number(randomLoanNumber)
                .mobileNumber(mobileNumber)
                .type(LoanType.HOME)
                .total(LoanAmount.ONE_HUNDRED_THOUSAND)
                .repaid(0)
                .remaining(LoanAmount.ONE_HUNDRED_THOUSAND)
                .build();
        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoan(String mobileNumber) {
        return loanRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanNotFoundException(mobileNumber));
    }

    @Override
    public Loan updateLoan(LoanDTO loanDTO) {
        final Long loanNumber = loanDTO.getNumber();
        final Loan loan = loanRepository
                .findByNumber(loanNumber)
                .orElseThrow(() -> new LoanNotFoundException(loanNumber));
        LoanMapper.map(loanDTO, loan);
        calculateRemaining(loan);
        return loanRepository.save(loan);
    }

    private void calculateRemaining(Loan loan) {
        final Integer total = loan.getTotal();
        final Integer repaid = loan.getRepaid();
        loan.setRemaining(total - repaid);
    }

    @Override
    public Loan deleteLoan(String mobileNumber) {
        return loanRepository
                .deleteByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanNotFoundException(mobileNumber));
    }
}
