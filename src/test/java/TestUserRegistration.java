import java.util.Scanner;

public class TestUserRegistration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // �����û���Ϣ
        System.out.println("�������û���:");
        String username = scanner.nextLine();

        System.out.println("����������:");
        String password = scanner.nextLine();

        System.out.println("����������:");
        String email = scanner.nextLine();

        System.out.println("������绰:");
        String phone = scanner.nextLine();

        System.out.println("�������û����� (��ְ��/��Ƹ��/����Ա):");
        String userType = scanner.nextLine();

        // ���� User ����
        User user = new User(0, username, password, email, phone, userType);  // 0��ʾ���û���id�����ݿ��Զ�����
        UserDAO userDAO = new UserDAO();

        // ע���û�
        if (userDAO.registerUser(user)) {
            System.out.println("ע��ɹ���");
        } else {
            System.out.println("ע��ʧ�ܣ�");
        }

        scanner.close();
    }
}
