import java.util.Scanner;

public class TestUserLogin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入用户名和密码
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();

        System.out.println("请输入密码:");
        String password = scanner.nextLine();

        UserDAO userDAO = new UserDAO();

        // 登录验证
        if (userDAO.loginUser(username, password)) {
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }

        scanner.close();
    }
}
