package com.askie01.loans.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class ErrorResponseDTO {
    private String path;
    private Integer code;
    private String message;
    private LocalDateTime timestamp;
}
