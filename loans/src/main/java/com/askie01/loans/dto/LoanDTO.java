package com.askie01.loans.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class LoanDTO {

    @PositiveOrZero(message = "Loan's id cannot be negative")
    private Long id;

    @NotEmpty(message = "Loan's mobile number cannot be null/empty/blank")
    @Pattern(regexp = "(^$|\\d{9})", message = "Loan's mobile number must be 9 digits")
    private String mobileNumber;

    @NotNull(message = "Loan's number cannot be null/empty/blank")
    @Size(min = 12, max = 12, message = "Loan's number length have to be 12 digits")
    private Long number;

    @NotNull(message = "Loan's type cannot be null")
    @NotEmpty(message = "Loan type cannot be a null or empty")
    private String type;

    @NotNull(message = "Loan's total cannot be null")
    @PositiveOrZero(message = "Loan's total cannot be negative")
    private Integer total;

    @NotNull(message = "Loan's repaid cannot be null")
    @PositiveOrZero(message = "Loan's repaid cannot be negative")
    private Integer repaid;

    @Null
    private Integer remaining;
}
