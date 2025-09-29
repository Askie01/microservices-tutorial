package com.askie01.cards.service;

import com.askie01.cards.constant.CardLimit;
import com.askie01.cards.constant.CardType;
import com.askie01.cards.dto.CardDTO;
import com.askie01.cards.entity.Card;
import com.askie01.cards.exception.CardAlreadyExistsException;
import com.askie01.cards.exception.CardNotFoundException;
import com.askie01.cards.mapper.CardMapper;
import com.askie01.cards.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CoreCardService implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card createCard(String mobileNumber) {
        final boolean cardExists = cardRepository
                .findByMobileNumber(mobileNumber)
                .isPresent();
        if (cardExists) {
            throw new CardAlreadyExistsException(mobileNumber);
        }
        return createDefaultCard(mobileNumber);
    }

    private Card createDefaultCard(String mobileNumber) {
        final long randomCardNumber = 100_000_000_000L
                + ThreadLocalRandom.current().nextInt(900_000_000);
        final Card card = Card.builder()
                .number(randomCardNumber)
                .mobileNumber(mobileNumber)
                .type(CardType.CREDIT)
                .moneyLimit(CardLimit.ONE_HUNDRED_THOUSAND)
                .debt(0)
                .balance(CardLimit.ONE_HUNDRED_THOUSAND)
                .build();
        return cardRepository.save(card);
    }

    @Override
    public Card getCard(String mobileNumber) {
        return cardRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardNotFoundException(mobileNumber));
    }

    @Override
    public Card updateCard(CardDTO cardDTO) {
        final Long cardNumber = cardDTO.getNumber();
        final Card card = cardRepository
                .findByNumber(cardNumber)
                .orElseThrow(() -> new CardNotFoundException(cardNumber));
        CardMapper.map(cardDTO, card);
        return cardRepository.save(card);
    }

    @Override
    public Card deleteCard(String mobileNumber) {
        final Card card = getCard(mobileNumber);
        cardRepository.delete(card);
        return card;
    }
}
