package usingcollection.dao;

import java.util.HashMap;

import usingcollection.Exceptions.AccountNotFoundException;
import usingcollection.Exceptions.LowBalanceException;
import usingcollection.bean.BankBean;

public class BankDao implements BankDaoI {

	HashMap h1;

	public BankDao() {

		h1 = new HashMap();
	}

	// bean class object

	BankBean bean = new BankBean();

	// to check account already exists or not

	public BankBean checkAccount(long accNo) {

		if (h1.containsKey(accNo)) {
			
			bean = (BankBean) h1.get(accNo);
			return bean;

		} else

			return null;
	}

	// to put the data into the map

	public void setData(long accNo, BankBean bean) {

		h1.put(accNo, bean);
	}


}
