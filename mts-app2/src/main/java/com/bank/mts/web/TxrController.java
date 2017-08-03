package com.bank.mts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.mts.service.AccountBalanceException;
import com.bank.mts.service.TxrService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/txr")
public class TxrController {

	@Autowired
	private TxrService txrService;

	@RequestMapping(method = RequestMethod.POST)
	public TxrResponse txr(@RequestBody TxrRequest request) {
		String m = txrService.doTxr(request.getAmount(), request.getFromAccNum(), request.getToAccNum());
		TxrResponse response = new TxrResponse();
		response.setMessage(m);
		return response;
	}

	@ExceptionHandler(value = { AccountBalanceException.class })
	public TxrResponse TxrFailHandler(Exception e) {
		TxrResponse response = new TxrResponse();
		response.setMessage(e.getMessage());
		return response;
	}

}
