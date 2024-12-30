import java.util.List;
import java.util.Scanner;

public class TestJobApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("��������ְ��ID��");
        int applicantId = scanner.nextInt();

        System.out.println("������ְλID��");
        int jobId = scanner.nextInt();

        ApplicationDAO applicationDAO = new ApplicationDAO();

        // ��ְ������ְλ
        if (applicationDAO.applyForJob(jobId, applicantId)) {
            System.out.println("ְλ����ɹ���");
        } else {
            System.out.println("ְλ����ʧ�ܣ�");
        }

        // ��Ƹ�߲鿴ְλ������
        System.out.println("��������Ƹ�߲鿴��ְλID��");
        int employerJobId = scanner.nextInt();
        List<Application> applications = applicationDAO.getApplicationsForJob(employerJobId);
        if (applications.isEmpty()) {
            System.out.println("û�������ְλ����ְ�ߣ�");
        } else {
            System.out.println("�����ְλ����ְ�ߣ�");
            for (Application application : applications) {
                System.out.println("����ID: " + application.getApplicationId());
                System.out.println("��ְ��ID: " + application.getApplicantId());
                System.out.println("״̬: " + application.getStatus());
                System.out.println("����ʱ��: " + application.getAppliedAt());
                System.out.println("----");
            }
        }

        // ��Ƹ�߸�������״̬
        System.out.println("������Ҫ���µ�����ID��");
        int applicationId = scanner.nextInt();
        System.out.println("�������µ�����״̬�������/ͨ��/�ܾ�����");
        String status = scanner.next();
        if (applicationDAO.updateApplicationStatus(applicationId, status)) {
            System.out.println("����״̬���³ɹ���");
        } else {
            System.out.println("����״̬����ʧ�ܣ�");
        }
    }
}
