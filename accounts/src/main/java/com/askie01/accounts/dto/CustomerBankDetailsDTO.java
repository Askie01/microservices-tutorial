package com.askie01.accounts.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class CustomerBankDetailsDTO {
    private AccountDTO account;
    private CardDTO card;
    private LoanDTO loan;
}
