package com.interview.creditcard.service;

import com.interview.creditcard.entity.CreditCardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CreditCardService {

    List<CreditCardEntity> getAllCreditCards( );
    Page<CreditCardEntity> getAllCreditCards(PageRequest pageRequest);
    CreditCardEntity addCreditCard(CreditCardEntity creditCardEntity);

}
