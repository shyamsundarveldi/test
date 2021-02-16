package com.hsbc.jpa.repository;

import com.hsbc.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}