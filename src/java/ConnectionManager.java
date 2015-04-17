
import java.sql.Connection;
import java.sql.DriverManager;

class ConnectionManager {

	public static final String USERNAME = "root";
	public static final String PASSWORD = "toor";
	public static final String DATABASE_NAME = "pdfmaker";
	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/"
			+ DATABASE_NAME;

	public static Connection createConnectionToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DATABASE_URL,
					USERNAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
