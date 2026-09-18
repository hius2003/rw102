import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("QUESTION 1:");
        System.out.printf("%d\n", 5);

        System.out.println("QUESTION 2:");
        System.out.printf("%,d\n", 100000000);

        System.out.println("QUESTION 3:");
        System.out.printf("%.4f\n", 5.567098);

        System.out.println("QUESTION 4:");
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.\n", "Nguyễn Văn A");

        System.out.println("QUESTION 5:");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH'h':mm'p':ss's'");
        System.out.printf("%s\n", now.format(formatter));

        System.out.println("QUESTION 6:");
        Account[] accounts = { TestData.account1, TestData.account2, TestData.account3 };
        for (Account acc : accounts) {
            String deptName = (acc.department != null) ? acc.department.name : "Chưa có phòng ban";
            System.out.printf("Email: %s | Full Name: %s | Department: %s\n", acc.email, acc.fullName, deptName);
        }
    }
}