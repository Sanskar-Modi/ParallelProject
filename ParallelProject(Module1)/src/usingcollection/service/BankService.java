package usingcollection.service;

import java.util.List;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankBean;
import usingcollection.bean.BankTranscation;
import usingcollection.dao.BankDao;
import usingcollection.dao.BankDaoI;

public class BankService implements BankServiceI {

	BankDaoI dao = new BankDao();
	boolean res;

	// to get the detials if account existe or not and add account
	public boolean getDetails(String name, String add, long accNo, String phone, int pin, int bal) {

		BankBean bean = new BankBean();
		if (dao.checkAccount(accNo)) {

			bean.setAccNo(accNo);
			bean.setAdd(add);
			bean.setBalance(bal);
			bean.setName(name);
			bean.setPhone(phone);
			bean.setPin(pin);
			bean.setTrans("Account Created \n Amount deposited  : "+bal);
			dao.setData(accNo, bean);
			return true;
		} else
			return false;

	}

	// to show balance

	public int displayBalance(long accNo, int pass) throws AccountNotFoundException

	{
		int bal = dao.displayBalance(accNo, pass);
		return bal;
	}

	// to validate if account and pin matches or not

	public boolean validateAccount(long accNo, int pass) throws AccountNotFoundException {
		res = dao.validateAccount(accNo, pass);
		return res;
	}

	// to deposit
	public int deposit(long accNo, int pass, int deposit_amount) throws AccountNotFoundException {

		res = validateAccount(accNo, pass);
		if (res) {
			int amount = 0;
			amount = dao.deposit(accNo, pass, deposit_amount);
			return amount;
		} else
			throw new AccountNotFoundException();
	}

	// to withdraw
	public int withdraw(long accNo, int pass, int withdraw_amount) throws AccountNotFoundException {

		res = validateAccount(accNo, pass);
		if (res) {
			int amount = 0;
			amount = dao.withdraw(accNo, pass, withdraw_amount);
			return amount;
		} else
			throw new AccountNotFoundException();
	}

	// to validate balance avaialble
	public boolean validateBalance(long accNo, int pass, int withdraw_amount)
			throws AccountNotFoundException, LowBalanceException {

		res = dao.validateBalance(accNo, pass, withdraw_amount);
		return res;
	}

	// to transfer fund
	public boolean transferFund(long accNo, long accNo1, int transfer_amount, int pin)
			throws AccountNotFoundException, LowBalanceException {

		res = dao.transferFund(accNo, accNo1, transfer_amount, pin);
		return res;

	}

	// to validate account exists or not
	@Override
	public boolean validateAccount(long accNo1) {

		res = dao.validateAccount(accNo1);
		return res;
	}

	@Override
	public String getTrans(long accNo, int pin) throws AccountNotFoundException {

		String str = dao.getTrans(accNo,pin);
		return str;
	}


	

}
