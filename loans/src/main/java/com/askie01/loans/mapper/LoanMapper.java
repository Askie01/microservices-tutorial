package com.askie01.loans.mapper;

import com.askie01.loans.dto.LoanDTO;
import com.askie01.loans.entity.Loan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoanMapper {

    public static LoanDTO mapToLoanDTO(Loan loan) {
        final LoanDTO loanDTO = new LoanDTO();
        map(loan, loanDTO);
        return loanDTO;
    }

    public static void map(Loan source, LoanDTO target) {
        target.setNumber(source.getNumber());
        target.setType(source.getType());
        target.setMobileNumber(source.getMobileNumber());
        target.setTotal(source.getTotal());
        target.setRepaid(source.getRepaid());
        target.setRemaining(source.getRemaining());
    }

    public static Loan mapToLoan(LoanDTO loanDTO) {
        final Loan loan = new Loan();
        map(loanDTO, loan);
        return loan;
    }

    public static void map(LoanDTO source, Loan target) {
        target.setNumber(source.getNumber());
        target.setType(source.getType());
        target.setMobileNumber(source.getMobileNumber());
        target.setTotal(source.getTotal());
        target.setRepaid(source.getRepaid());
        target.setRemaining(source.getRemaining());
    }
}
