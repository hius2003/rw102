import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Exercise3 {
    public static void main(String[] args) {
        Exam exam1 = TestData.exam1;

        System.out.println("QUESTION 1:");
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd 'Tháng' MM, yyyy", new Locale("vi", "VN"));
        System.out.println("Exam 1 create date: " + exam1.createDate.format(fmt1));

        System.out.println("QUESTION 2:");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy - MM - dd - HH - mm - ss");
        System.out.println("Exam đã tạo ngày: " + exam1.createDate.format(fmt2));

        System.out.println("QUESTION 3:");
        System.out.println("Năm: " + exam1.createDate.format(DateTimeFormatter.ofPattern("yyyy")));

        System.out.println("QUESTION 4:");
        System.out.println("Tháng - Năm: " + exam1.createDate.format(DateTimeFormatter.ofPattern("MM - yyyy")));

        System.out.println("QUESTION 5:");
        System.out.println("MM-DD: " + exam1.createDate.format(DateTimeFormatter.ofPattern("MM-dd")));
    }
}