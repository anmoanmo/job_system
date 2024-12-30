import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // ��ȡ�����û���Ϣ
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT user_id, username, password, email, phone, user_type FROM users";  // ֻ��ѯ User ����Ҫ���ֶ�
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {  // ֱ���� try-with-resources �г�ʼ��

            while (resultSet.next()) {
                // ���� User ����ʱ��ֻʹ�� User �������е��ֶ�
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

    // ɾ���û�
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ��ȡ����ְλ��Ϣ
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT job_id, company_name, job_title, job_description, location, salary, employer_id FROM jobs";  // ����ѯʱ����ֶ�
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {  // �����µ� try �鴦�� ResultSet

                while (resultSet.next()) {
                    // ���� Job ����ʱ��ֻʹ�� Job �������е��ֶ�
                    Job job = new Job(
                            resultSet.getInt("job_id"),
                            resultSet.getString("company_name"),
                            resultSet.getString("job_title"),
                            resultSet.getString("job_description"),
                            resultSet.getString("location"),
                            resultSet.getDouble("salary"),  // ʹ�� double ����
                            resultSet.getInt("employer_id")
                    );
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // ���ͨ��ְλ
    public boolean approveJob(int jobId) {
        String query = "UPDATE jobs SET status = 'ͨ��' WHERE job_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jobId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // �ܾ�ְλ
    public boolean rejectJob(int jobId) {
        String query = "UPDATE jobs SET status = '�ܾ�' WHERE job_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jobId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ��ȡϵͳͳ����Ϣ�����û�������ְλ��������������
    public String getSystemStats() {
        StringBuilder stats = new StringBuilder();
        String query = "SELECT COUNT(*) AS user_count FROM users";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {  // �����µ� try �鴦�� ResultSet

                if (resultSet.next()) {
                    stats.append("���û���: ").append(resultSet.getInt("user_count")).append("\n");
                }
            }

            query = "SELECT COUNT(*) AS job_count FROM jobs";
            try (ResultSet resultSet = statement.executeQuery(query)) {  // �����µ� try �鴦�� ResultSet
                if (resultSet.next()) {
                    stats.append("��ְλ��: ").append(resultSet.getInt("job_count")).append("\n");
                }
            }

            query = "SELECT COUNT(*) AS application_count FROM applications";
            try (ResultSet resultSet = statement.executeQuery(query)) {  // �����µ� try �鴦�� ResultSet
                if (resultSet.next()) {
                    stats.append("��ְλ������: ").append(resultSet.getInt("application_count")).append("\n");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats.toString();
    }
}
