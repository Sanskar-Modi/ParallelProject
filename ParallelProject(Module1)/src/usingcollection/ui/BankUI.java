package usingcollection.ui;

import java.util.List;
import java.util.Scanner;
import usingcollection.Exceptions.*;
import usingcollection.bean.BankTranscation;
import usingcollection.service.BankService;
import usingcollection.service.BankServiceI;

public class BankUI {

	public static void main(String[] args)

			throws AccountNotFoundException, LowBalanceException, LengthException, InvalidPinException,
			MinimumBalanceException, InvalidFormatException {

		// variables
		BankTranscation trans = new BankTranscation();

		String cont = "yes";
		int choice;
		long accNo, accNo1;
		int withdraw_amount, deposit_amount, transfer_amount;
		int pin;
		int amount = 0;
		int balance;
		boolean res;
		boolean found;

		BankServiceI service = new BankService();

		Scanner sc = new Scanner(System.in);

		// To ask choice from users and perform operations
		while (cont.equals("yes")) {

			System.out.println("------------Welcome to Capgemini Bank----------");
			System.out.println("Press 1 to Create Account");
			System.out.println("Press 2 to Show Balance");
			System.out.println("Press 3 to Deposit");
			System.out.println("Press 4 to Withdraw");
			System.out.println("Press 5 to Transer Fund");
			System.out.println("Press 6 to Print Transcations");
			System.out.println("Press 7 to Exit");
			System.out.println("Enter Choice");

			choice = sc.nextInt();

			switch (choice) {

			// to create account

			case 1:

				System.out.println("Enter name");
				String name = sc.nextLine();
				name += sc.nextLine();

				String regexUserName = "^[A-Za-z\\s]+$";
				if (!name.matches(regexUserName)) {
					throw new InvalidFormatException();
				}

				System.out.println("Enter address ");
				String add = sc.nextLine();

				if (!add.matches(regexUserName)) {
					throw new InvalidFormatException();
				}

				System.out.println("Enter phone number");
				String phone = sc.next();

				if (phone.length() < 10) {
					throw new LengthException();
				}

				accNo = Long.parseLong(phone) - 10000;

				System.out.println("Enter Pin");
				pin = sc.nextInt();
				if (String.valueOf(pin).length() < 4 || String.valueOf(pin).length() > 4) {
					throw new InvalidPinException();
				}

				System.out.println("Enter Balance");
				int bal = sc.nextInt();
				if (bal < 1000) {
					throw new MinimumBalanceException();
				}

				res = service.getDetails(name, add, accNo, phone, pin, bal);

				if (res == true) {
					System.out.println("Account Created Successfully !!!");
					System.out.println("Account Number : " + accNo);
					trans.setAccount(accNo);
					trans.setAmount(bal);
					trans.setType("Create");
					trans.setTrans_id(1000);

				} else {
					System.out.println("Cannot Create Account");
				}

				break;

			// to show balance
			case 2:

				System.out.println("Enter account number");
				accNo = sc.nextLong();

				System.out.println("Enter password");
				pin = sc.nextInt();

				balance = service.displayBalance(accNo, pin);

				System.out.println("Balance :" + balance);

				break;

			// to deposit

			case 3:

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter password");
				pin = sc.nextInt();

				res = service.validateAccount(accNo, pin);

				if (res) {
					System.out.println("Enter amount to be deposited");
					deposit_amount = sc.nextInt();

					amount = service.deposit(accNo, pin, deposit_amount);

					System.out.println("Amount deposited : " + deposit_amount);

					System.out.println("Updated Balance : " + service.displayBalance(accNo, pin));
					
					trans.setAccount(accNo);
					trans.setAmount(deposit_amount);
					trans.setType("Deposit");
					trans.setTrans_id(1001);

				} else {
					throw new AccountNotFoundException();
				}

				break;

			// to withdraw

			case 4:

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter password");
				pin = sc.nextInt();

				res = service.validateAccount(accNo, pin);

				if (res) {

					System.out.println("Enter amount to be withdraw");
					withdraw_amount = sc.nextInt();

					res = service.validateBalance(accNo, pin, withdraw_amount);

					if (res) {

						amount = service.withdraw(accNo, pin, withdraw_amount);

						System.out.println("Amount Withdrawn : " + withdraw_amount);
						System.out.println("Updated Balance : " + service.displayBalance(accNo, pin));
						
						trans.setAccount(accNo);
						trans.setAmount(withdraw_amount);
						trans.setType("Withdraw");
						trans.setTrans_id(1002);
						

					} else {

						throw new LowBalanceException();
					}

				} else {

					throw new AccountNotFoundException();
				}
				break;

			// to transfer fund

			case 5:

				System.out.println("Enter account no");
				accNo = sc.nextLong();

				System.out.println("Enter password");
				pin = sc.nextInt();

				res = service.validateAccount(accNo, pin);

				if (res) {

					System.out.println("Enter account to which you want to transfer fund");
					accNo1 = sc.nextLong();

					found = service.validateAccount(accNo1);

					if (found) {

						System.out.println("Enter amount to transfer");
						transfer_amount = sc.nextInt();
						res = service.transferFund(accNo, accNo1, transfer_amount, pin);

					} else {
						throw new AccountNotFoundException();
					}
					if (res) {
						System.out.println("Amount transferred Successfully");
						System.out.println(
								"Updated balance for Account " + accNo + " : " + service.displayBalance(accNo, pin));
						System.out.println(
								"Updated balance for Account " + accNo1 + " : " + service.displayBalance(accNo1, pin));
					} else {
						System.out.println("Cannot transfer amount");
					}

				} else {
					throw new AccountNotFoundException();
				}
				break;

			// to show transactions
			case 6:
				System.out.println("Enter account number");
				accNo = sc.nextLong();
				System.out.println("Enter pin");
				pin = sc.nextInt();
				res = service.validateAccount(accNo, pin);

				if (res) {
				
					String str = service.getTrans(accNo,pin);
					System.out.println(str);

				} else {
					throw new AccountNotFoundException();
				}

				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Please Enter choice between 1 - 7 ");

			}

			System.out.println("Press yes to continue and no to exit  ");

			cont = sc.next();

			if (cont.equalsIgnoreCase("no")) {
				System.out.println("Bye !!!");
				System.exit(0);
			}
		}
		sc.close();
	}

}
