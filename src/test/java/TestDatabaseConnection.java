import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        // ���Ի�ȡ���ݿ�����
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("���ݿ����ӳɹ���");
            try {
                connection.close();  // �ر�����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("���ݿ�����ʧ�ܣ�");
        }
    }
}
