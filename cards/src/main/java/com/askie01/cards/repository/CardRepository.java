package com.askie01.cards.repository;

import com.askie01.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByMobileNumber(String mobileNumber);

    Optional<Card> findByNumber(Long number);

    Optional<Card> deleteByMobileNumber(String mobileNumber);
}
