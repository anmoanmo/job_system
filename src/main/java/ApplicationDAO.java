import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // ��ְ������ְλ
    public boolean applyForJob(int jobId, int applicantId) {
        String query = "INSERT INTO applications (job_id, applicant_id, status) VALUES (?, ?, '�����')";
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

    // ��Ƹ�߲鿴ְλ����������
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

    // ��Ƹ�߸���ְλ�����״̬
    public boolean updateApplicationStatus(int applicationId, String status) {
        if (!status.equals("�����") && !status.equals("ͨ��") && !status.equals("�ܾ�")) {
            return false; // ״̬���Ϸ�
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

    // ��ְ�߲鿴�Լ��������¼
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
