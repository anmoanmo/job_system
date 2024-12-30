import java.util.Scanner;

public class TestUserRegistration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入用户信息
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();

        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        System.out.println("请输入邮箱:");
        String email = scanner.nextLine();

        System.out.println("请输入电话:");
        String phone = scanner.nextLine();

        System.out.println("请输入用户类型 (求职者/招聘者/管理员):");
        String userType = scanner.nextLine();

        // 创建 User 对象
        User user = new User(0, username, password, email, phone, userType);  // 0表示新用户，id由数据库自动生成
        UserDAO userDAO = new UserDAO();

        // 注册用户
        if (userDAO.registerUser(user)) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }

        scanner.close();
    }
}
