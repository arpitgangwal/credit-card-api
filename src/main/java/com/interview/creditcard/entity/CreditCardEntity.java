package com.interview.creditcard.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "creditcard")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Data
public class CreditCardEntity {
    @Id
    @Column(name = "cardNumber")
    private Long cardNumber;
    @NotNull
    @Column(name = "username")
    private String username;
    @NotNull
    @Column(name = "cardType")
    private String cardType;
    @NotNull
    @Column(name = "expirationDate")
    private Date expirationDate;
    @NotNull
    @Column(name = "securityCode")
    private String securityCode;
    @NotNull
    @Column(name= "bankName")
    private String bankName;
    @Column(name = "balance")
    @ColumnDefault("0.00")
    private double balance;
}