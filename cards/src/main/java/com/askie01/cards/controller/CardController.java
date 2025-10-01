package com.askie01.cards.controller;

import com.askie01.cards.constant.ResponseCode;
import com.askie01.cards.constant.ResponseMessage;
import com.askie01.cards.dto.CardDTO;
import com.askie01.cards.dto.ResponseDTO;
import com.askie01.cards.entity.Card;
import com.askie01.cards.mapper.CardMapper;
import com.askie01.cards.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cards")
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createCard(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                  @RequestParam String mobileNumber) {
        cardService.createCard(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.CREATED)
                .message(ResponseMessage.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CardDTO> getCard(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                           @RequestParam String mobileNumber) {
        final Card card = cardService.getCard(mobileNumber);
        final CardDTO cardDTO = CardMapper.mapToCardDTO(card);
        return new ResponseEntity<>(cardDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) {
        cardService.updateCard(cardDTO);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteCard(@Pattern(regexp = "(^$|\\d{9})", message = "Mobile number must be 9 digits")
                                                  @RequestParam String mobileNumber) {
        cardService.deleteCard(mobileNumber);
        final ResponseDTO response = ResponseDTO.builder()
                .code(ResponseCode.OK)
                .message(ResponseMessage.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
