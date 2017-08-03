package com.bank.mts.web;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "txr-response")
public class TxrResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
