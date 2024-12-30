import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSearchDAO {

    // ���ݹؼ��֡��ص��н�ʷ�Χ����ְλ��ѯ��֧�ַ�ҳ
    public List<Job> searchJobs(String keyword, String location, Double minSalary, Double maxSalary, int pageNo, int pageSize) {
        List<Job> jobs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM jobs WHERE 1=1");

        // �����������
        if (keyword != null && !keyword.isEmpty()) {
            query.append(" AND (job_title LIKE ? OR company_name LIKE ?)");
        }
        if (location != null && !location.isEmpty()) {
            query.append(" AND location LIKE ?");
        }
        if (minSalary != null) {
            query.append(" AND salary >= ?");
        }
        if (maxSalary != null) {
            query.append(" AND salary <= ?");
        }

        // ��ӷ�ҳ
        query.append(" LIMIT ?, ?");

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            
            int parameterIndex = 1;

            if (keyword != null && !keyword.isEmpty()) {
                statement.setString(parameterIndex++, "%" + keyword + "%");
                statement.setString(parameterIndex++, "%" + keyword + "%");
            }
            if (location != null && !location.isEmpty()) {
                statement.setString(parameterIndex++, "%" + location + "%");
            }
            if (minSalary != null) {
                statement.setDouble(parameterIndex++, minSalary);
            }
            if (maxSalary != null) {
                statement.setDouble(parameterIndex++, maxSalary);
            }

            // ���÷�ҳ
            statement.setInt(parameterIndex++, (pageNo - 1) * pageSize);
            statement.setInt(parameterIndex, pageSize);

            ResultSet resultSet = statement.executeQuery();
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

    // ͳ�Ʒ���������ְλ���������ڷ�ҳ
    public int countJobs(String keyword, String location, Double minSalary, Double maxSalary) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM jobs WHERE 1=1");

        // �����������
        if (keyword != null && !keyword.isEmpty()) {
            query.append(" AND (job_title LIKE ? OR company_name LIKE ?)");
        }
        if (location != null && !location.isEmpty()) {
            query.append(" AND location LIKE ?");
        }
        if (minSalary != null) {
            query.append(" AND salary >= ?");
        }
        if (maxSalary != null) {
            query.append(" AND salary <= ?");
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            
            int parameterIndex = 1;

            if (keyword != null && !keyword.isEmpty()) {
                statement.setString(parameterIndex++, "%" + keyword + "%");
                statement.setString(parameterIndex++, "%" + keyword + "%");
            }
            if (location != null && !location.isEmpty()) {
                statement.setString(parameterIndex++, "%" + location + "%");
            }
            if (minSalary != null) {
                statement.setDouble(parameterIndex++, minSalary);
            }
            if (maxSalary != null) {
                statement.setDouble(parameterIndex++, maxSalary);
            }

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
