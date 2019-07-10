package usingcollection.dao;

import java.util.List;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankBean;
import usingcollection.bean.BankTranscation;

public interface BankDaoI {
	

	public boolean checkAccount(long accNo);

	public int displayBalance(long accNo, int pass) throws AccountNotFoundException;

	public boolean validateAccount(long accNo, int pass) throws AccountNotFoundException;

	public boolean transferFund(long accNo, long accNo1, int transfer_amount, int pin) throws AccountNotFoundException, LowBalanceException;

	public void setData(long accNo, BankBean bean);

	public int deposit(long accNo, int pass, int deposit_amount) throws AccountNotFoundException;

	public int withdraw(long accNo, int pass, int withdraw_amount) throws AccountNotFoundException;

	public boolean validateBalance(long accNo, int pass, int withdraw_amount) throws AccountNotFoundException, LowBalanceException;


	public boolean validateAccount(long accNo1);

	public String getTrans(long accNo, int pin) throws AccountNotFoundException;


	







}
