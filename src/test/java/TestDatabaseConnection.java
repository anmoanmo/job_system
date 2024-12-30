import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        // 尝试获取数据库连接
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("数据库连接成功！");
            try {
                connection.close();  // 关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("数据库连接失败！");
        }
    }
}
