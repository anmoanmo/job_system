import java.util.List;

public class TestGetAllUsers {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        
        // 管理员查询所有用户
        List<User> users = userDAO.getAllUsers();
        
        // 输出所有用户信息
        for (User user : users) {
            System.out.println("用户ID: " + user.getUserId());
            System.out.println("用户名: " + user.getUsername());
            System.out.println("邮箱: " + user.getEmail());
            System.out.println("电话: " + user.getPhone());
            System.out.println("用户类型: " + user.getUserType());
            System.out.println("------------------------------");
        }
    }
}
