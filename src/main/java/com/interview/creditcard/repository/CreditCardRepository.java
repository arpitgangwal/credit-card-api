package com.interview.creditcard.repository;

import com.interview.creditcard.entity.CreditCardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

}
