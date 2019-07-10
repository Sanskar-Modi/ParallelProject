package usingcollection.service;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankTranscation;

import java.util.List;

public interface BankServiceI {
	
	public boolean getDetails(String name, String add,long accNo, String phone,  int pin, int bal);

	public boolean validateAccount(long accNo, int pin) throws AccountNotFoundException;

	public int deposit(long accNo, int pin, int deposit_amount) throws AccountNotFoundException;

	public int displayBalance(long accNo, int pin) throws AccountNotFoundException;

	public boolean validateBalance(long accNo, int pin, int withdraw_amount) throws AccountNotFoundException, LowBalanceException;

	public int withdraw(long accNo, int pin, int withdraw_amount) throws AccountNotFoundException;

	public boolean transferFund(long accNo, long accNo1, int transfer_amount, int pin) throws AccountNotFoundException, LowBalanceException;

	public boolean validateAccount(long accNo1);

	public String getTrans(long accNo, int pin) throws AccountNotFoundException;


	

	

}
