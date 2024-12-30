import java.util.List;

public class TestGetAllUsers {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        
        // ����Ա��ѯ�����û�
        List<User> users = userDAO.getAllUsers();
        
        // ��������û���Ϣ
        for (User user : users) {
            System.out.println("�û�ID: " + user.getUserId());
            System.out.println("�û���: " + user.getUsername());
            System.out.println("����: " + user.getEmail());
            System.out.println("�绰: " + user.getPhone());
            System.out.println("�û�����: " + user.getUserType());
            System.out.println("------------------------------");
        }
    }
}
