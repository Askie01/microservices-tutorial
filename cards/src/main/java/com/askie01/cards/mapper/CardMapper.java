package com.askie01.cards.mapper;

import com.askie01.cards.dto.CardDTO;
import com.askie01.cards.entity.Card;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardMapper {

    public static CardDTO mapToCardDTO(Card card) {
        final CardDTO cardDTO = new CardDTO();
        map(card, cardDTO);
        return cardDTO;
    }

    public static void map(Card source, CardDTO target) {
        target.setNumber(source.getNumber());
        target.setType(source.getType());
        target.setMobileNumber(source.getMobileNumber());
        target.setLimit(source.getMoneyLimit());
        target.setBalance(source.getBalance());
        target.setDebt(source.getDebt());
    }

    public static Card mapToCard(CardDTO cardDTO) {
        final Card card = new Card();
        map(cardDTO, card);
        return card;
    }

    public static void map(CardDTO source, Card target) {
        target.setNumber(source.getNumber());
        target.setType(source.getType());
        target.setMobileNumber(source.getMobileNumber());
        target.setMoneyLimit(source.getLimit());
        target.setBalance(source.getBalance());
        target.setDebt(target.getDebt());
    }
}
