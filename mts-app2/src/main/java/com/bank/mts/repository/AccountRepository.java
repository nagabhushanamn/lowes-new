package com.bank.mts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.mts.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
