import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exercise2 {

    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();

        Department dept1 = new Department();
        dept1.name = "Sale";

        Department dept2 = new Department();
        dept2.name = "Marketing";

        Account acc1 = new Account();
        acc1.email = "acc1@gmail.com";
        acc1.fullName = "Nguyễn Văn A";
        acc1.department = dept1;

        Account acc2 = new Account();
        acc2.email = "acc2@gmail.com";
        acc2.fullName = "Trần Văn B";
        acc2.department = dept2;

        Account acc3 = new Account();
        acc3.email = "acc3@gmail.com";
        acc3.fullName = "Lê Văn C";

        Account[] accounts = { acc1, acc2, acc3 };
        question6(accounts);
    }

    // Question 1:
    public static void question1() {
        System.out.println("Question 1");
        int number = 5;
        System.out.printf("%d\n", number);
    }

    // Question 2:
    public static void question2() {
        System.out.println("Question 2");
        int number = 100000000;
        System.out.printf("%,d\n", number);
    }

    // Question 3:
    public static void question3() {
        System.out.println("Question 3");
        double number = 5.567098;
        System.out.printf("%.4f\n", number);
    }

    // Question 4:
    public static void question4() {
        System.out.println("Question 4");
        String fullName = "Nguyễn Văn A";
        System.out.printf("Tên tôi là" + " " + fullName + " " +  "và tôi đang độc thân.\n");
    }

    // Question 5:
    public static void question5() {
        System.out.println("Question 5");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.printf("%s\n", now.format(formatter));
    }

    // Question 6:
    public static void question6(Account[] accounts) {
        System.out.println("===== Question 6 =====");
        // In tiêu đề cột
        System.out.printf("%-20s | %-20s | %-20s\n", "Email", "Full Name", "Department");
        System.out.println("------------------------------------------------------------------");

        for (Account acc : accounts) {
            String deptName = (acc.department != null) ? acc.department.name : "Chưa có phòng ban";
            System.out.printf("%-20s | %-20s | %-20s\n", acc.email, acc.fullName, deptName);
        }
    }
}