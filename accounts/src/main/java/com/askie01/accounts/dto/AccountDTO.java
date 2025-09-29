package com.askie01.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class AccountDTO {

    @PositiveOrZero(message = "Account's id cannot be negative")
    private Long id;

    @NotBlank(message = "Account's type cannot be null/empty/blank")
    private String type;

    @NotBlank(message = "Account's branch address cannot be null/empty/blank")
    private String branchAddress;
    private CustomerDTO customerDTO;
}
