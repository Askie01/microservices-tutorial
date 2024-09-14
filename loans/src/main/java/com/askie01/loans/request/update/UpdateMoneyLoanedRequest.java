package com.askie01.loans.request.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateMoneyLoanedRequest {

    @Min(value = 1000_0000_0000_0000L, message = "Loan's number must be at least 16 digits")
    @Max(value = 9999_9999_9999_9999L, message = "Loan's number must be at most 16 digits")
    private Long number;

    @Min(value = 100_000_000, message = "Loan's mobile number must be at least 9 digits")
    @Max(value = 999_999_999, message = "Loan's mobile number must be at most 9 digits")
    private Integer mobileNumber;

    @NotNull(message = "Loaned money cannot be null")
    @Positive(message = "Loan's money loaned must be above zero")
    private Integer moneyLoaned;
}
