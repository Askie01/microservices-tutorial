package com.askie01.accounts.dto;

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
public class CardDTO {

    @PositiveOrZero(message = "Card's id cannot be negative")
    private Long id;

    @NotBlank(message = "Card's mobile number cannot be null/empty/blank")
    @Pattern(regexp = "(^$|\\d{9})", message = "Card's mobile number must be 9 digits")
    private String mobileNumber;

    @NotNull(message = "Card's number cannot be null")
    @Min(value = 100_000_000_000L, message = "Card's number must be 12 digits")
    @Max(value = 999_999_999_999L, message = "Card's number must be 12 digits")
    private Long number;

    @NotBlank(message = "Card's type cannot be null/empty/blank")
    private String type;

    @NotNull(message = "Card's balance cannot be null")
    @PositiveOrZero(message = "Card's balance cannot be negative")
    private Integer balance;

    @NotNull(message = "Card's debt cannot be null")
    @PositiveOrZero(message = "Card's debt cannot be negative")
    private Integer debt;

    @NotNull(message = "Card's limit cannot be null")
    @PositiveOrZero(message = "Card's limit cannot be negative")
    private Integer limit;
}
