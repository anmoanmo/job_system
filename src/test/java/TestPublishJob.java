import java.util.Scanner;

public class TestPublishJob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("�����빫˾����:");
        String companyName = scanner.nextLine();

        System.out.println("������ְλ����:");
        String jobTitle = scanner.nextLine();

        System.out.println("������ְλ����:");
        String jobDescription = scanner.nextLine();

        System.out.println("������ְλ�����ص�:");
        String location = scanner.nextLine();

        System.out.println("������ְλн��:");
        double salary = scanner.nextDouble();

        System.out.println("��������Ƹ�ߵ��û�ID:");
        int employerId = scanner.nextInt();

        // ����ְλ����
        Job job = new Job(0, companyName, jobTitle, jobDescription, location, salary, employerId);

        JobDAO jobDAO = new JobDAO();

        // ����ְλ
        if (jobDAO.publishJob(job)) {
            System.out.println("ְλ�����ɹ���");
        } else {
            System.out.println("ְλ����ʧ�ܣ�");
        }
    }
}
