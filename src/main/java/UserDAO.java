import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 注册用户
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password, email, phone, user_type) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // 设置参数
            statement.setString(1, user.getUsername());  // 用户名
            statement.setString(2, user.getPassword());  // 密码
            statement.setString(3, user.getEmail());     // 邮箱
            statement.setString(4, user.getPhone());     // 电话
            statement.setString(5, user.getUserType());  // 用户类型
            
            // 执行插入操作
            int rowsAffected = statement.executeUpdate();
            
            // 如果插入成功，返回 true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 注册失败
    }

    // 用户登录
    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // 设置查询参数
            statement.setString(1, username);
            statement.setString(2, password);  // 验证密码
            
            // 执行查询
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // 如果找到匹配的记录，说明登录成功
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 登录失败
    }

    //更新信息
    public boolean updateUserInfo(int userId, String email, String phone, String userType) {
        String query = "UPDATE users SET email = ?, phone = ?, user_type = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // 设置参数
            statement.setString(1, email);
            statement.setString(2, phone);
            statement.setString(3, userType);
            statement.setInt(4, userId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询
    public User getUserById(int userId) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("user_type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //查询所有用户
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("user_type")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
