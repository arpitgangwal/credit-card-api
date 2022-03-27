package com.interview.creditcard.service;

import com.interview.creditcard.entity.CreditCardEntity;
import com.interview.creditcard.repository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public Page<CreditCardEntity> getAllCreditCards(PageRequest pageRequest) {
            log.debug("Fetching ", pageRequest.getPageSize()," credit cards for page", pageRequest.getPageNumber());
            return creditCardRepository.findAll(pageRequest);
    }

    @Override
    public List<CreditCardEntity> getAllCreditCards() {
            log.debug("Fetching all credit cards records");
            return creditCardRepository.findAll();
    }

    @Override
    public CreditCardEntity addCreditCard(CreditCardEntity creditCardEntity) {
        CreditCardEntity cardEntity= creditCardRepository.save(creditCardEntity);
        log.debug("credit card added", cardEntity);
        return cardEntity;
    }
}
