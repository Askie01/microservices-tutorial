package com.askie01.loans.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Loan extends BaseEntity {
    private String mobileNumber;
    private Long number;
    private String type;
    private Integer total;
    private Integer repaid;
    private Integer remaining;
}
