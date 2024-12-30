import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/job_system";  // ���ݿ�URL
    private static final String USER = "root";  // ���ݿ��û���
    private static final String PASSWORD = "";  // ���ݿ�����

    public static Connection getConnection() {
        try {
            // ����JDBC����
            Class.forName("com.mysql.cj.jdbc.Driver");
            // ��ȡ���ݿ�����
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
