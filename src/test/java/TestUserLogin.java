import java.util.Scanner;

public class TestUserLogin {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // �����û���������
        System.out.println("�������û���:");
        String username = scanner.nextLine();

        System.out.println("����������:");
        String password = scanner.nextLine();

        UserDAO userDAO = new UserDAO();

        // ��¼��֤
        if (userDAO.loginUser(username, password)) {
            System.out.println("��¼�ɹ���");
        } else {
            System.out.println("�û������������");
        }

        scanner.close();
    }
}
