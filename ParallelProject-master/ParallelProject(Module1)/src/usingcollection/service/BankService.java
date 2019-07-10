package usingcollection.service;

import usingcollection.Exceptions.*;
import usingcollection.bean.BankBean;
import usingcollection.dao.BankDao;
import usingcollection.dao.BankDaoI;

public class BankService implements BankServiceI {

	BankDaoI dao = new BankDao();
	BankBean bank = new BankBean();
	BankBean bank1 = new BankBean();

	
	boolean res;

	// to get the details if account exists or not and add account

	public boolean createAccount(String name, String add, long accNo, String phone, int pin, int bal)
			throws AccountAlreadyExistsException {

		BankBean bean = new BankBean();
		boolean res = false;

		if (dao.checkAccount(accNo) == null) {

			bean.setAccNo(accNo);
			bean.setAdd(add);
			bean.setBalance(bal);
			bean.setName(name);
			bean.setPhone(phone);
			bean.setPin(pin);
			bean.setTrans("Account Created \n Amount deposited  : " + bal);

			dao.setData(accNo, bean);

			res = true;
		}

		else

		{
			res = false;
			throw new AccountAlreadyExistsException();
		}
		return res;

	}

	// to show balance

	public int showBalance(long accNo) throws AccountNotFoundException {
		
		bank = dao.checkAccount(accNo);
		int balance = 0;
		if (bank == null) {
			throw new AccountNotFoundException();
		} else {
			balance = bank.getBalance();
		}

		return balance;
	}

	// to deposit

	public int deposit(long accNo, int deposit_amount) throws AccountNotFoundException {

		int balance = 0;
		bank = dao.checkAccount(accNo);

		if (bank == null) {
			throw new AccountNotFoundException();
		} else {

			balance = bank.setBalance(bank.getBalance() + deposit_amount);

			dao.setData(accNo, bank);
		}

		return balance;
	}

	// to withdraw

	public int withdraw(long accNo, int withdraw_amount) throws AccountNotFoundException {

	int balance = 0;
		bank = dao.checkAccount(accNo);
		if (bank == null) {
			throw new AccountNotFoundException();
		} else {
			balance = bank.setBalance(bank.getBalance() - withdraw_amount);

			dao.setData(accNo, bank);
		}

		return balance;
	}

	// to transfer fund

	public boolean transferfund(long accNo, long accNo1, int transfer_amount) throws AccountNotFoundException {

		bank = dao.checkAccount(accNo);

		if (!(bank == null)) {

			bank1 = dao.checkAccount(accNo1);

			if (!(bank1 == null))

			{

				int sender_balance = bank.getBalance();
				int reciever_balance = bank1.getBalance();

				bank.setBalance(sender_balance - transfer_amount);
				bank1.setBalance(reciever_balance + transfer_amount);

				dao.setData(accNo, bank);
				dao.setData(accNo1, bank1);
			}

			else {
				throw new AccountNotFoundException();
			}
		} else {
			throw new AccountNotFoundException();
		}

		return true;
	}

}
