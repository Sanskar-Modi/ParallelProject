package usingcollection.bean;

import java.util.List;

public class BankTranscation {
	
	private String type;
	private int amount;
	private long account;
	private int trans_id;
	
	List<BankTranscation> transaction ;
	
	public List<BankTranscation> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<BankTranscation> transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "BankTranscation type=" + type + "\n amount=" + amount + "\n account=" + account + "\n trans_id="
				+ trans_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getAccount() {
		return account;
	}
	public void setAccount(long accNo) {
		this.account = accNo;
	}
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	
	

}
