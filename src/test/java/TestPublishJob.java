import java.util.Scanner;

public class TestPublishJob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入公司名称:");
        String companyName = scanner.nextLine();

        System.out.println("请输入职位名称:");
        String jobTitle = scanner.nextLine();

        System.out.println("请输入职位描述:");
        String jobDescription = scanner.nextLine();

        System.out.println("请输入职位工作地点:");
        String location = scanner.nextLine();

        System.out.println("请输入职位薪资:");
        double salary = scanner.nextDouble();

        System.out.println("请输入招聘者的用户ID:");
        int employerId = scanner.nextInt();

        // 创建职位对象
        Job job = new Job(0, companyName, jobTitle, jobDescription, location, salary, employerId);

        JobDAO jobDAO = new JobDAO();

        // 发布职位
        if (jobDAO.publishJob(job)) {
            System.out.println("职位发布成功！");
        } else {
            System.out.println("职位发布失败！");
        }
    }
}
