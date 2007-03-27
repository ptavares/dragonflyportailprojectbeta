package projects.adds.tools;

public class Tools {
	
	/**
	 * Look for Special characters in the String string
	 * @param string The string to analyse
	 * @return true if no special characters are found, false otherwise
	 */
	public static  boolean checkSpecialChar(String string) {

		for(int i=0; i<string.length() ; i++){
			if(!Character.isLetterOrDigit(string.charAt(i)))
				if(!(string.charAt(i)=='_'))		
					if(!(Character.isWhitespace(string.charAt(i))) )
						return false;
		}

		return true;
	}

}
