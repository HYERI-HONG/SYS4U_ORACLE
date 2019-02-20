package version3.kr.sys4u.jdbc.exception;

public class EmployeeException extends RuntimeException {

	private static final long serialVersionUID = 8693355479102987728L;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeException(String message) {
		super(message);
	}

	public EmployeeException(Throwable cause) {
		super(cause);
	}
	
	

}
