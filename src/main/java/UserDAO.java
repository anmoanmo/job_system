import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ע���û�
    public boolean registerUser(User user) {
        String query = "INSERT INTO users (username, password, email, phone, user_type) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // ���ò���
            statement.setString(1, user.getUsername());  // �û���
            statement.setString(2, user.getPassword());  // ����
            statement.setString(3, user.getEmail());     // ����
            statement.setString(4, user.getPhone());     // �绰
            statement.setString(5, user.getUserType());  // �û�����
            
            // ִ�в������
            int rowsAffected = statement.executeUpdate();
            
            // �������ɹ������� true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // ע��ʧ��
    }

    // �û���¼
    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // ���ò�ѯ����
            statement.setString(1, username);
            statement.setString(2, password);  // ��֤����
            
            // ִ�в�ѯ
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // ����ҵ�ƥ��ļ�¼��˵����¼�ɹ�
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // ��¼ʧ��
    }

    //������Ϣ
    public boolean updateUserInfo(int userId, String email, String phone, String userType) {
        String query = "UPDATE users SET email = ?, phone = ?, user_type = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            // ���ò���
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

    //��ѯ
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

    //��ѯ�����û�
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
