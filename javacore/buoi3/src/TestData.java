import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestData {
    public static Department department1 = new Department();
    public static Department department2 = new Department();
    public static Department department3 = new Department();

    public static Position position1 = new Position();
    public static Position position2 = new Position();

    public static Account account1 = new Account();
    public static Account account2 = new Account();
    public static Account account3 = new Account();

    public static Group group1 = new Group();
    public static Group group2 = new Group();
    public static Group group3 = new Group();

    public static Exam exam1 = new Exam();

    static {
        department1.id = 1; department1.name = "Sale";
        department2.id = 2; department2.name = "Marketing";
        department3.id = 3; department3.name = "Bảo vệ";

        position1.id = 1; position1.name = Position.PositionName.DEV;
        position2.id = 2; position2.name = Position.PositionName.TEST;

        account1.id = 1; account1.email = "Email 1"; account1.username = "Username 1";
        account1.fullName = "Nguyễn Văn A"; account1.department = department1;
        account1.position = position1; account1.createDate = LocalDate.of(2020, 1, 1);

        account2.id = 2; account2.email = "Email 2"; account2.username = "Username 2";
        account2.fullName = "Trần Văn B"; account2.department = department2;
        account2.position = position2; account2.createDate = LocalDate.of(2021, 2, 2);

        account3.id = 3; account3.email = "Email 3"; account3.username = "Username 3";
        account3.fullName = "Lê Văn C"; account3.createDate = LocalDate.of(2022, 3, 3);

        group1.id = 1; group1.name = "Java Fresher"; group1.accounts = new Account[]{ account1, account2 };
        group2.id = 2; group2.name = "C# Fresher"; group2.accounts = new Account[]{ account2, account3 };
        group3.id = 3; group3.name = "Python Fresher"; group3.accounts = new Account[]{ account1, account3 };

        account1.groups = new Group[]{ group1, group3 };
        account2.groups = new Group[]{ group1, group2 };

        exam1.id = 1; exam1.code = "EX001"; exam1.title = "Đề thi Java Core";
        exam1.createDate = LocalDateTime.of(2021, 5, 15, 10, 30, 45);
    }
}