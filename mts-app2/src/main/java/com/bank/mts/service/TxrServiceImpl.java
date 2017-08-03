package com.bank.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.mts.model.Account;
import com.bank.mts.repository.AccountRepository;

@Service
public class TxrServiceImpl implements TxrService {

	private AccountRepository accountRepository;

	@Autowired
	public TxrServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public String doTxr(double amount, String fromAccNum, String toAccNum) {

		// Load FromAccount
		Account fromAccount = accountRepository.findOne(fromAccNum);
		// Load ToAccount
		Account toAccount = accountRepository.findOne(toAccNum);

		// debit & credit
		if (fromAccount.getBalance() >= amount) {
			fromAccount.setBalance(fromAccount.getBalance() - amount);
			toAccount.setBalance(toAccount.getBalance() + amount);
		} else {
			throw new AccountBalanceException("no enough balance");
		}

		// update ToAccount
		accountRepository.save(toAccount);
		// update FromAccount
		accountRepository.save(fromAccount);

		return "Txr Success";
	}

}
