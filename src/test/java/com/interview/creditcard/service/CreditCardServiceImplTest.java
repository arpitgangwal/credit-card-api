package com.interview.creditcard.service;

import com.interview.creditcard.entity.CreditCardEntity;
import com.interview.creditcard.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreditCardServiceImplTest {
  @Autowired
    private CreditCardServiceImpl creditCardServiceImpl;
  @MockBean
   private CreditCardRepository creditCardRepository;

    @Test
    void testGetAllCreditCardsWithPagination() {
        when(creditCardRepository.findAll(PageRequest.of(1,10))).thenReturn(new Page<CreditCardEntity>() {
            @Override
            public int getTotalPages() {
                return 01;
            }

            @Override
            public long getTotalElements() {
                return 10;
            }

            @Override
            public <U> Page<U> map(Function<? super CreditCardEntity, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<CreditCardEntity> getContent() {
                return new ArrayList<>();
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<CreditCardEntity> iterator() {
                return null;
            }
        });
        assertNotNull(creditCardServiceImpl.getAllCreditCards(PageRequest.of(1,10)));
    }

    @Test
    void testGetAllCreditCards() {
        when(creditCardRepository.findAll()).thenReturn(new ArrayList<CreditCardEntity>());
        assertNotNull(creditCardServiceImpl.getAllCreditCards());
        assertEquals(creditCardServiceImpl.getAllCreditCards().getClass(), ArrayList.class);
    }

    @Test
    void addCreditCard() {
        CreditCardEntity creditCardEntity = new CreditCardEntity(11111111l,"Arpit Gangwal","visa",new Date(),"123","icici",0.00);
        when(creditCardRepository.save(creditCardEntity)).thenReturn(creditCardEntity);
        CreditCardEntity creditCardEntityResult= creditCardServiceImpl.addCreditCard(creditCardEntity);
        assertNotNull(creditCardEntityResult);
        assertEquals(creditCardEntity,creditCardEntityResult);

    }
}