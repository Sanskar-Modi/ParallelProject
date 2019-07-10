package usingcollection.Exceptions;


public class InvalidPinException extends Exception{
	
	public InvalidPinException()

	{
		
	}

	public String toString() {
		return "Pin Should be of 4 digits ";
	}
}
