package fr.umlv.dragonflyBdd.exception;

/**
 * Exception witch can be throwed when an error appened in DataBase 
 * 
 * @author Patrick
 *
 */
public class DragonflyBddException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * see {@link java.lang.Exception#Exception()}.
	 *
	 */
	public DragonflyBddException() {
		super();
	}
	
	/**
	 * see {@link java.lang.Exception#Exception(String)}.
	 *
	 */
	public DragonflyBddException(String message) {
		super(message);
	}
	
	/**
	 * see {@link java.lang.Exception#Exception(String, Throwable)}.
	 *
	 */
	public DragonflyBddException(String message, Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * Constructs a new exception with the specified exception.
	 * @param exception the exception.
	 */
	public DragonflyBddException(Exception exception) {
		this(exception.getMessage(), exception.getCause());
	}
	

}
