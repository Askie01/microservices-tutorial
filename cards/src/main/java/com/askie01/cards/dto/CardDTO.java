package com.askie01.cards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Card's number cannot be null/empty/blank")
    @Size(min = 12, max = 12, message = "Card's number length have to be 12 digits")
    private Long number;

    @NotBlank(message = "Card's type cannot be null/empty/blank")
    private String type;

    @PositiveOrZero(message = "Card's balance cannot be negative")
    private int balance;

    @PositiveOrZero(message = "Card's debt cannot be negative")
    private Integer debt;

    @PositiveOrZero(message = "Card's limit cannot be negative")
    private Integer limit;
}
