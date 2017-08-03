package com.bank.mts.service;

public interface TxrService {

	String doTxr(double amount, String fromAccNum, String toAccNum);

}
