package usingcollection.Exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException()
	{
		
	}

	public String toString() {
		
		return "Account Not Found . Please Enter a valid account number!!!";
	}
	
	
	
}
