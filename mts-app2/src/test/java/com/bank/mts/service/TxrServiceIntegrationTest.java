package com.bank.mts.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.mts.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class TxrServiceIntegrationTest {

	@Autowired
	private TxrService txrService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Transactional
	@Test
	public void shouldTxrSuccessWithEnoughBalance() {
		String actual = txrService.doTxr(100.00, "2", "3");
		assertThat(actual, is("Txr Success"));
	}

	@Transactional
	@Test
	public void shouldTxrFailWithLessBalance() {
		thrown.expect(AccountBalanceException.class);
		thrown.expectMessage("no enough balance");

		txrService.doTxr(1000000.00, "2", "3");
	}

}
