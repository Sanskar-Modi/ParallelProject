package usingcollection.Exceptions;


public class MinimumBalanceException extends Exception{

	@Override
	public String toString() {
		
		return "Minimum Balance should be 1000";
	}

	public MinimumBalanceException()
	{
	
	}
}
