import java.util.Scanner;

public class TestUpdateUserInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入用户ID和新信息
        System.out.println("请输入用户ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();  // 吸收换行符

        System.out.println("请输入新的邮箱:");
        String email = scanner.nextLine();

        System.out.println("请输入新的电话:");
        String phone = scanner.nextLine();

        System.out.println("请输入新的用户类型 (求职者/招聘者/管理员):");
        String userType = scanner.nextLine();

        UserDAO userDAO = new UserDAO();

        // 更新用户信息
        if (userDAO.updateUserInfo(userId, email, phone, userType)) {
            System.out.println("用户信息修改成功！");
        } else {
            System.out.println("用户信息修改失败！");
        }

        scanner.close();
    }
}
