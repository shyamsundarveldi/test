package com.hsbc.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    private long balance;
    private String transactions;
    private String productType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false, updatable = false)
    private Customer customer;
}
