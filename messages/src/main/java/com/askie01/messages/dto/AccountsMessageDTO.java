package com.askie01.messages.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class AccountsMessageDTO {
    private Long accountNumber;
    private String name;
    private String email;
    private String mobileNumber;
}
