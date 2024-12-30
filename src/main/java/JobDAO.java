import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    // 发布职位
    public boolean publishJob(Job job) {
        String query = "INSERT INTO jobs (company_name, job_title, job_description, location, salary, employer_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, job.getCompanyName());
            statement.setString(2, job.getJobTitle());
            statement.setString(3, job.getJobDescription());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getSalary());
            statement.setInt(6, job.getEmployerId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 查询所有职位
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String query = "SELECT * FROM jobs";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Job job = new Job(
                        resultSet.getInt("job_id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("job_title"),
                        resultSet.getString("job_description"),
                        resultSet.getString("location"),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("employer_id")
                );
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // 根据职位ID查询职位
    public Job getJobById(int jobId) {
        Job job = null;
        String query = "SELECT * FROM jobs WHERE job_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, jobId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                job = new Job(
                        resultSet.getInt("job_id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("job_title"),
                        resultSet.getString("job_description"),
                        resultSet.getString("location"),
                        resultSet.getDouble("salary"),
                        resultSet.getInt("employer_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }
}
