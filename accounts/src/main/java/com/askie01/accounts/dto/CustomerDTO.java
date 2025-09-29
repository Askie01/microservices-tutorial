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
public class CustomerDTO {

    @PositiveOrZero(message = "Customer's id cannot be negative")
    private Long id;

    @NotBlank(message = "Customer's name cannot be null/empty/blank")
    @Size(min = 5, max = 30, message = "Customer's name length have to be between 5 and 30")
    private String name;

    @NotBlank(message = "Customer's email address cannot be null/empty/blank")
    @Email(message = "Customer's email address should be a valid email value")
    private String email;

    @NotBlank(message = "Customer's mobile number cannot be null/empty/blank")
    @Pattern(regexp = "(^$|\\d{9})", message = "Customer's mobile number must be 9 digits")
    private String mobileNumber;
}
