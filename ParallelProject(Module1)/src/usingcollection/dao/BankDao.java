package usingcollection.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankBean;
import usingcollection.bean.BankTranscation;

public class BankDao implements BankDaoI {

	HashMap h1;

	public BankDao() {

		h1 = new HashMap();
	}

	// bean class object

	BankBean bean = new BankBean();

	// to check account already exists or not

	public boolean checkAccount(long accNo) {

		if (!h1.containsKey(accNo)) {
			return true;

		} else

			return false;
	}

	// to put the data into the map

	public void setData(long accNo, BankBean bean) {

		h1.put(accNo, bean);
	}

	// to display balance

	public int displayBalance(long accNo, int pass) throws AccountNotFoundException {

		boolean res = validateAccount(accNo, pass);
		if (res) {

			bean = (BankBean) h1.get(accNo);
			return bean.getBalance();

		} else {
			throw new AccountNotFoundException();
		}

	}

	// to validate correct account and pin

	public boolean validateAccount(long accNo, int pass) throws AccountNotFoundException {

		boolean res = false;
		bean = (BankBean) h1.get(accNo);

		if (h1.containsKey(accNo)) {
			if (bean.getPin() == pass) {
				res = true;
			} else {
				res = false;
			}
		}

		else {
			throw new AccountNotFoundException();
		}

		return res;

	}

	// to deposit
	public int deposit(long accNo, int pass, int deposit_amount) throws AccountNotFoundException {

		boolean res = validateAccount(accNo, pass);
		int amount = 0;

		if (res)

		{
			bean = (BankBean) h1.get(accNo);

			int balance = bean.getBalance();

			amount = bean.setBalance(balance + deposit_amount);
			bean.setBalance(amount);
			String str = bean.getTrans() + "\n Credited with :" + deposit_amount;
			bean.setTrans(str);
			setData(accNo, bean);

			return amount;
		} else {
			throw new AccountNotFoundException();
		}

	}

	// to withdraw
	public int withdraw(long accNo, int pass, int withdraw_amount) throws AccountNotFoundException {

		boolean res = validateAccount(accNo, pass);
		int amount = 0;

		if (res)

		{
			bean = (BankBean) h1.get(accNo);
			int balance = bean.getBalance();
			amount = bean.setBalance(balance - withdraw_amount);
			bean.setBalance(amount);
			String str = bean.getTrans() + "\n Debitted with :" + withdraw_amount;
			bean.setTrans(str);
			setData(accNo, bean);

			return amount;
		} else {
			throw new AccountNotFoundException();
		}
	}

	// to validate balance

	public boolean validateBalance(long accNo, int pass, int amount)
			throws AccountNotFoundException, LowBalanceException {

		boolean res = validateAccount(accNo, pass);

		if (res) {
			bean = (BankBean) h1.get(accNo);

			int balance = bean.getBalance();

			if (balance >= amount) {

				res = true;
			} else {

				throw new LowBalanceException();
			}

		} else {
			res = false;
		}
		return res;
	}

	// to transfer fund

	public boolean transferFund(long accNo, long accNo1, int transfer_amount, int pin)
			throws AccountNotFoundException, LowBalanceException {

		BankBean bean = (BankBean) h1.get(accNo);
		int balance = bean.getBalance();

		BankBean bean1 = (BankBean) h1.get(accNo1);
		int balance_1 = bean1.getBalance();

		boolean res = validateBalance(accNo, pin, transfer_amount);

		if (res) {
			bean.setBalance(balance - transfer_amount);
			bean1.setBalance(balance_1 + transfer_amount);
			String str = bean.getTrans() + "\n Debitted with :" + transfer_amount;
			bean.setTrans(str);
			String str1 = bean1.getTrans() + "\n Credited with :" + transfer_amount;
			bean1.setTrans(str1);
			res = true;
		} else {
			res = false;
		}
		return res;

	}

	// to validate account exists or not

	@Override
	public boolean validateAccount(long accNo1) {

		boolean res;

		System.out.println(h1.get(accNo1));
		if (h1.containsKey(accNo1)) {
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	@Override
	public String getTrans(long accNo, int pin) throws AccountNotFoundException {
		
		boolean res = validateAccount(accNo,pin);
		String str = null;
		if(res)
		{
		if (h1.containsKey(accNo)) {
			{
				bean = (BankBean) h1.get(accNo);
				 str = bean.getTrans();
				
			}
		}
		
	}
		return str;

}
}
