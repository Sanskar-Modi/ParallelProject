package usingcollection.service;

import usingcollection.Exceptions.AccountAlreadyExistsException;
import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankTranscation;

import java.util.List;

public interface BankServiceI {
	
	public boolean createAccount(String name, String add, long accNo, String phone, int pin, int bal) throws AccountAlreadyExistsException;
	
	public int showBalance(long accNo) throws AccountNotFoundException;
	
	public int deposit(long accNo, int deposit_amount) throws AccountNotFoundException;
	
	public int withdraw(long accNo, int withdraw_amount) throws AccountNotFoundException;
	
	public boolean transferfund(long accNo , long accNo1,int transfer_amount) throws AccountNotFoundException;

	

	

}
