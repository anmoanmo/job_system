import java.util.Scanner;

public class TestUpdateUserInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // �����û�ID������Ϣ
        System.out.println("�������û�ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();  // ���ջ��з�

        System.out.println("�������µ�����:");
        String email = scanner.nextLine();

        System.out.println("�������µĵ绰:");
        String phone = scanner.nextLine();

        System.out.println("�������µ��û����� (��ְ��/��Ƹ��/����Ա):");
        String userType = scanner.nextLine();

        UserDAO userDAO = new UserDAO();

        // �����û���Ϣ
        if (userDAO.updateUserInfo(userId, email, phone, userType)) {
            System.out.println("�û���Ϣ�޸ĳɹ���");
        } else {
            System.out.println("�û���Ϣ�޸�ʧ�ܣ�");
        }

        scanner.close();
    }
}
