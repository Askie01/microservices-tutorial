package com.askie01.cards.entity;

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
public class Card extends BaseEntity {
    private String mobileNumber;
    private Long number;
    private String type;
    private Integer balance;
    private Integer debt;
    private Integer limit;
}
