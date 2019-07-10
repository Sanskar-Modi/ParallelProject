package usingcollection.Exceptions;

public class LengthException extends Exception{

	public LengthException()
	{
		
	}

	@Override
	public String toString() {
		return "Phone Number Should be of 10 digits";
	}
	
}
