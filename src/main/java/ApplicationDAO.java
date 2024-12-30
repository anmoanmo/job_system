import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // 求职者申请职位
    public boolean applyForJob(int jobId, int applicantId) {
        String query = "INSERT INTO applications (job_id, applicant_id, status) VALUES (?, ?, '待审核')";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jobId);
            statement.setInt(2, applicantId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 招聘者查看职位的所有申请
    public List<Application> getApplicationsForJob(int jobId) {
        List<Application> applications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE job_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, jobId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Application application = new Application(
                        resultSet.getInt("application_id"),
                        resultSet.getInt("job_id"),
                        resultSet.getInt("applicant_id"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("applied_at")
                );
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // 招聘者更新职位申请的状态
    public boolean updateApplicationStatus(int applicationId, String status) {
        if (!status.equals("待审核") && !status.equals("通过") && !status.equals("拒绝")) {
            return false; // 状态不合法
        }

        String query = "UPDATE applications SET status = ? WHERE application_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setInt(2, applicationId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 求职者查看自己的申请记录
    public List<Application> getApplicationsByApplicant(int applicantId) {
        List<Application> applications = new ArrayList<>();
        String query = "SELECT * FROM applications WHERE applicant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, applicantId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Application application = new Application(
                        resultSet.getInt("application_id"),
                        resultSet.getInt("job_id"),
                        resultSet.getInt("applicant_id"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("applied_at")
                );
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
}
