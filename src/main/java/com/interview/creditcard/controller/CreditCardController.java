package com.interview.creditcard.controller;

import com.interview.creditcard.entity.CreditCardEntity;
import com.interview.creditcard.model.CreditCard;
import com.interview.creditcard.service.CreditCardService;
import com.interview.creditcard.utility.CreditCardUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/credit-cards")
@Slf4j
public class CreditCardController {
    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditCardEntity> createCreditCard(@Validated @RequestBody CreditCard creditCard) throws ParseException {
        log.info("Add credit card request received ", creditCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.addCreditCard(CreditCardUtility.mapCreditCardObject(creditCard)));
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCreditCardList(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        log.info("List credit cards request received ");
        if (page != null && size != null)
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(creditCardService.getAllCreditCards(PageRequest.of(page, size)));
        return ResponseEntity.status(HttpStatus.OK).body(creditCardService.getAllCreditCards());

    }
}
