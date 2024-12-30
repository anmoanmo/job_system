import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // 获取所有用户信息
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT user_id, username, password, email, phone, user_type FROM users";  // 只查询 User 类需要的字段
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {  // 直接在 try-with-resources 中初始化

            while (resultSet.next()) {
                // 创建 User 对象时，只使用 User 类中已有的字段
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

    // 删除用户
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

    // 获取所有职位信息
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT job_id, company_name, job_title, job_description, location, salary, employer_id FROM jobs";  // 不查询时间戳字段
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {  // 创建新的 try 块处理 ResultSet

                while (resultSet.next()) {
                    // 创建 Job 对象时，只使用 Job 类中已有的字段
                    Job job = new Job(
                            resultSet.getInt("job_id"),
                            resultSet.getString("company_name"),
                            resultSet.getString("job_title"),
                            resultSet.getString("job_description"),
                            resultSet.getString("location"),
                            resultSet.getDouble("salary"),  // 使用 double 类型
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

    // 审核通过职位
    public boolean approveJob(int jobId) {
        String query = "UPDATE jobs SET status = '通过' WHERE job_id = ?";
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

    // 拒绝职位
    public boolean rejectJob(int jobId) {
        String query = "UPDATE jobs SET status = '拒绝' WHERE job_id = ?";
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

    // 获取系统统计信息（总用户数，总职位数，总申请数）
    public String getSystemStats() {
        StringBuilder stats = new StringBuilder();
        String query = "SELECT COUNT(*) AS user_count FROM users";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {  // 创建新的 try 块处理 ResultSet

                if (resultSet.next()) {
                    stats.append("总用户数: ").append(resultSet.getInt("user_count")).append("\n");
                }
            }

            query = "SELECT COUNT(*) AS job_count FROM jobs";
            try (ResultSet resultSet = statement.executeQuery(query)) {  // 创建新的 try 块处理 ResultSet
                if (resultSet.next()) {
                    stats.append("总职位数: ").append(resultSet.getInt("job_count")).append("\n");
                }
            }

            query = "SELECT COUNT(*) AS application_count FROM applications";
            try (ResultSet resultSet = statement.executeQuery(query)) {  // 创建新的 try 块处理 ResultSet
                if (resultSet.next()) {
                    stats.append("总职位申请数: ").append(resultSet.getInt("application_count")).append("\n");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats.toString();
    }
}
