import java.sql.SQLOutput;
import java.time.LocalDate;

public class Exercise1 {
    public static void main(String[] args) {
        //  ctrl + alt + L : format lại code
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sale";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";

        Department department3 = new Department();
        department3.id = 3;
        department3.name = "Bảo vệ";

        Position position1 = new Position();
        position1.id = 1;
        position1.name = Position.PositionName.DEV;

        Position position2 = new Position();
        position2.id = 2;
        position2.name = Position.PositionName.TEST;

        Position position3 = new Position();
        position3.id = 3;
        position3.name = Position.PositionName.SCRUM_MASTER;

        Position position4 = new Position();
        position4.id = 1;
        position4.name = Position.PositionName.PM;


        Account account1 = new Account();
        account1.id = 1;
        account1.email = "Email 1";
        account1.username = "Username 1";
        account1.fullName = "FullName 1";
        account1.department = department1;
        account1.position = position1;
        account1.createDate = LocalDate.of(2020, 1, 1);

        Account account2 = new Account();
        account2.id = 2;
        account2.email = "Email 2";
        account2.username = "Username 2";
        account2.fullName = "FullName 1";
        account2.department = department1;
        account2.position = position1;
        account2.createDate = LocalDate.of(2021, 2, 2);

        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Java Fresher";

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "C# Fresher";

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Python Fresher";

        //Quesition 1:
        System.out.println("QUESTION 1:");
        if (account2.department == null) {
            System.out.println("Nhan vien nay chua co phong ban");
        } else {
            System.out.println("Phong ban cua han vien nay la: " + account2.department.name);
        }

        //Question 2:
        //Question 3:
        System.out.println("QUESTION 3:");
        System.out.println(account2.department == null ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là: " + account2.department.name);

        //Question 4:
        System.out.println("QUESTION 4:");
        System.out.println(account1.position.name == Position.PositionName.DEV ? "Đây là Developer" : "Người này không phải là Developer");

        //Question 5:
        System.out.println("QUESTION 5:");
        Account[] accOfGroup1 = { account1, account2 };
        group1.accounts = accOfGroup1;

        int accountCount = 0;
        if (group1.accounts != null) {
            accountCount = group1.accounts.length;
        }

        switch (accountCount) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
                break;
        }
        //Question 6:
        System.out.println("QUESTION 6:");
        int groupCount = (account2.groups != null) ? account2.groups.length : 0;

        switch (groupCount) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.print("Group của nhân viên này là: ");
                for (Group g : account2.groups) {
                    System.out.print(g.name + " ");
                }
                System.out.println();
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                break;
        }
        //Question 7:
        System.out.println("QUESTION 7:");
        switch (account1.position.name) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }

        //Question 8:
        System.out.println("QUESTION 8:");
        Account[] accounts = { account1, account2 };
        for (Account acc : accounts) {
            String deptName = (acc.department != null) ? acc.department.name : "Chưa có phòng ban";
            System.out.println("Email: " + acc.email + " FullName: " + acc.fullName + " Phòng ban: " + deptName);
        }

        //Question 9:
        System.out.println("QUESTION 9:");
        Department[] departments = {department1, department2, department3};
        for (Department dept : departments) {
            System.out.println("ID: " + dept.id + " Name: " + dept.name);
        }
    }
}
