package com.transfer.Payment_Transfer.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance = new BigDecimal("10000.00");

    private String accountType = "Savings";

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
