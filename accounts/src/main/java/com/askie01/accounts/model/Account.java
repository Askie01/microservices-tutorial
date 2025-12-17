package com.askie01.accounts.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
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
public class Account extends BaseEntity {
    private String type;
    private String branchAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Customer customer;
}
