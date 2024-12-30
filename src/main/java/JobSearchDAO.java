import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSearchDAO {

    // 根据关键字、地点和薪资范围进行职位查询，支持分页
    public List<Job> searchJobs(String keyword, String location, Double minSalary, Double maxSalary, int pageNo, int pageSize) {
        List<Job> jobs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM jobs WHERE 1=1");

        // 添加搜索条件
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

        // 添加分页
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

            // 设置分页
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

    // 统计符合条件的职位数量，用于分页
    public int countJobs(String keyword, String location, Double minSalary, Double maxSalary) {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM jobs WHERE 1=1");

        // 添加搜索条件
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
