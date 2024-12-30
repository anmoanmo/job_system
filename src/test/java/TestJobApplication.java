import java.util.List;
import java.util.Scanner;

public class TestJobApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入求职者ID：");
        int applicantId = scanner.nextInt();

        System.out.println("请输入职位ID：");
        int jobId = scanner.nextInt();

        ApplicationDAO applicationDAO = new ApplicationDAO();

        // 求职者申请职位
        if (applicationDAO.applyForJob(jobId, applicantId)) {
            System.out.println("职位申请成功！");
        } else {
            System.out.println("职位申请失败！");
        }

        // 招聘者查看职位的申请
        System.out.println("请输入招聘者查看的职位ID：");
        int employerJobId = scanner.nextInt();
        List<Application> applications = applicationDAO.getApplicationsForJob(employerJobId);
        if (applications.isEmpty()) {
            System.out.println("没有申请该职位的求职者！");
        } else {
            System.out.println("申请该职位的求职者：");
            for (Application application : applications) {
                System.out.println("申请ID: " + application.getApplicationId());
                System.out.println("求职者ID: " + application.getApplicantId());
                System.out.println("状态: " + application.getStatus());
                System.out.println("申请时间: " + application.getAppliedAt());
                System.out.println("----");
            }
        }

        // 招聘者更新申请状态
        System.out.println("请输入要更新的申请ID：");
        int applicationId = scanner.nextInt();
        System.out.println("请输入新的申请状态（待审核/通过/拒绝）：");
        String status = scanner.next();
        if (applicationDAO.updateApplicationStatus(applicationId, status)) {
            System.out.println("申请状态更新成功！");
        } else {
            System.out.println("申请状态更新失败！");
        }
    }
}
