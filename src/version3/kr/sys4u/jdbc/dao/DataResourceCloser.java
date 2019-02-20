package version3.kr.sys4u.jdbc.dao;

import version3.kr.sys4u.jdbc.exception.EmployeeException;

public class DataResourceCloser {

	public static void close(AutoCloseable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				throw new EmployeeException(e);
			}
		}
	}

	public static void close(AutoCloseable closeable1, AutoCloseable closeable2) {

		try {
			if (closeable1 != null ) {
				closeable1.close();
			}else if(closeable2 != null) {
				closeable2.close();
			}
		} catch (Exception e) {
			throw new EmployeeException(e);
		}

	}

}
