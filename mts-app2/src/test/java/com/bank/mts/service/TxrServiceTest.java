package com.bank.mts.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.mts.model.Account;
import com.bank.mts.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class TxrServiceTest {

	@Mock
	private AccountRepository accountRepositoryMock;
	
	@InjectMocks
	private TxrServiceImpl txrService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private Account fromAccount;
	private Account toAccount;

	@Before
	public void setUp() {
		fromAccount = new Account("1", 1000.00);
		given(accountRepositoryMock.findOne("1")).willReturn(fromAccount);

		toAccount = new Account("2", 1000.00);
		given(accountRepositoryMock.findOne("2")).willReturn(toAccount);
	}

	@Test
	public void shouldTxrSuccessWithEnoughBalance() {

		String actual = txrService.doTxr(500.00, "1", "2");

		verify(accountRepositoryMock).findOne("1");
		verify(accountRepositoryMock).findOne("2");

		assertThat(fromAccount.getBalance(), is(500.00));
		assertThat(toAccount.getBalance(), is(1500.00));

		verify(accountRepositoryMock).save(fromAccount);
		verify(accountRepositoryMock).save(toAccount);

		assertThat(actual, is("Txr Success"));

	}

	@Test
	public void shouldTxrFailWithLessBalance() {

		thrown.expect(AccountBalanceException.class);
		thrown.expectMessage("no enough balance");

		txrService.doTxr(1500.00, "1", "2");

	}

}
