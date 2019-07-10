package usingcollection.dao;

import java.util.List;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankBean;
import usingcollection.bean.BankTranscation;

public interface BankDaoI {


	public BankBean checkAccount(long accNo);

	public void setData(long accNo, BankBean bean);











}
