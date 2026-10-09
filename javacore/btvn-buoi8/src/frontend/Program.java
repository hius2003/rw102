package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import entity.Account;
import entity.Department;
import entity.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AccountController ACCOUNT = new AccountController();
    private static final DepartmentController DEPARTMENT = new DepartmentController();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n===== QUAN LY =====");
            System.out.println("1. Quan ly Account");
            System.out.println("2. Quan ly Department");
            System.out.println("0. Thoat");
            int choice = readInt("Chon: ");
            try {
                switch (choice) {
                    case 1 -> accountMenu();
                    case 2 -> departmentMenu();
                    case 0 -> running = false;
                    default -> System.out.println("Lua chon khong hop le.");
                }
            } catch (SQLException e) {
                System.out.println("Loi database: " + e.getMessage());
            }
        }
        System.out.println("Da thoat chuong trinh.");
    }

    private static void accountMenu() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ACCOUNT ---");
            System.out.println("1. Hien thi tat ca Account");
            System.out.println("2. Tim Account theo username");
            System.out.println("3. Them Account");
            System.out.println("4. Sua username theo ID");
            System.out.println("5. Xoa Account theo ID");
            System.out.println("0. Quay lai");
            int choice = readInt("Chon: ");
            switch (choice) {
                case 1 -> printAccounts(ACCOUNT.getAllAccounts());
                case 2 -> findAccount();
                case 3 -> addAccount();
                case 4 -> updateUsername();
                case 5 -> deleteAccount();
                case 0 -> back = true;
                default -> System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void findAccount() throws SQLException {
        String username = readText("Nhap username: ");
        Account account = ACCOUNT.findAccountByUsername(username);
        if (account == null) {
            System.out.println("Khong tim thay Account.");
        } else {
            printAccount(account);
        }
    }

    private static void addAccount() throws SQLException {
        String username;
        do {
            username = readText("Username (5-50 ky tu): ");
            if (!ACCOUNT.isValidUsername(username)) {
                System.out.println("Username sai do dai hoac da ton tai. Nhap lai.");
            }
        } while (!ACCOUNT.isValidUsername(username));

        String email;
        do {
            email = readText("Email (5-50 ky tu): ");
            if (!ACCOUNT.isValidEmail(email)) {
                System.out.println("Email sai dinh dang/do dai hoac da ton tai. Nhap lai.");
            }
        } while (!ACCOUNT.isValidEmail(email));

        String fullname;
        do {
            fullname = readText("Full name (5-50 ky tu): ");
            if (fullname.trim().length() < 5 || fullname.trim().length() > 50) {
                System.out.println("Full name phai dai 5-50 ky tu.");
            }
        } while (fullname.trim().length() < 5 || fullname.trim().length() > 50);

        List<Department> departments = DEPARTMENT.getAllDepartments();
        System.out.println("Danh sach Department:");
        for (Department item : departments) {
            System.out.println(item.getId() + " - " + item.getName());
        }
        Department selectedDepartment = chooseDepartment(departments);

        List<Position> positions = ACCOUNT.getAllPositions();
        System.out.println("Danh sach Position:");
        for (Position item : positions) {
            System.out.println(item.getId() + " - " + item.getName());
        }
        Position selectedPosition = choosePosition(positions);

        Account account = new Account(0, username, email, fullname,
                selectedDepartment, selectedPosition);
        if (ACCOUNT.addAccount(account)) {
            System.out.println("Them Account thanh cong.");
        } else {
            System.out.println("Them Account that bai.");
        }
    }

    private static Department chooseDepartment(List<Department> departments) {
        while (true) {
            int id = readInt("Nhap ID Department: ");
            for (Department department : departments) {
                if (department.getId() == id) return department;
            }
            System.out.println("ID Department khong dung. Nhap lai.");
        }
    }

    private static Position choosePosition(List<Position> positions) {
        while (true) {
            int id = readInt("Nhap ID Position: ");
            for (Position position : positions) {
                if (position.getId() == id) return position;
            }
            System.out.println("ID Position khong dung. Nhap lai.");
        }
    }

    private static void updateUsername() throws SQLException {
        int id = readInt("Nhap ID Account can sua: ");
        if (!ACCOUNT.isAccountIdExists(id)) {
            System.out.println("ID khong phai so duong hoac khong ton tai.");
            return;
        }
        String username = readText("Nhap username moi (5-50 ky tu): ");
        if (ACCOUNT.updateUsernameById(id, username)) {
            System.out.println("Cap nhat username thanh cong.");
        } else {
            System.out.println("Username khong hop le/da ton tai, hoac cap nhat that bai.");
        }
    }

    private static void deleteAccount() throws SQLException {
        int id = readInt("Nhap ID Account can xoa: ");
        if (ACCOUNT.deleteAccountById(id)) {
            System.out.println("Xoa Account thanh cong.");
        } else {
            System.out.println("ID khong hop le/khong ton tai, hoac xoa that bai.");
        }
    }

    private static void departmentMenu() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- DEPARTMENT ---");
            System.out.println("1. Hien thi Department");
            System.out.println("2. Tim Department theo ten");
            System.out.println("3. Them Department");
            System.out.println("4. Xoa Department theo ID");
            System.out.println("5. Sua ten Department theo ID");
            System.out.println("0. Quay lai");
            int choice = readInt("Chon: ");
            switch (choice) {
                case 1 -> printDepartments(DEPARTMENT.getAllDepartments());
                case 2 -> findDepartment();
                case 3 -> addDepartment();
                case 4 -> deleteDepartment();
                case 5 -> updateDepartment();
                case 0 -> back = true;
                default -> System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void findDepartment() throws SQLException {
        String name = readText("Nhap ten Department: ");
        Department department = DEPARTMENT.findDepartmentByName(name);
        if (department == null) System.out.println("Khong tim thay Department.");
        else printDepartments(List.of(department));
    }

    private static void addDepartment() throws SQLException {
        String name = readText("Nhap ten Department moi: ");
        if (DEPARTMENT.addDepartment(new Department(0, name))) {
            System.out.println("Them Department thanh cong.");
        } else {
            System.out.println("Ten khong hop le hoac them that bai.");
        }
    }

    private static void deleteDepartment() throws SQLException {
        int id = readInt("Nhap ID Department can xoa: ");
        if (DEPARTMENT.deleteDepartmentById(id)) {
            System.out.println("Xoa Department thanh cong.");
        } else {
            System.out.println("Khong xoa duoc. ID co the sai hoac Department dang duoc Account su dung.");
        }
    }

    private static void updateDepartment() throws SQLException {
        int id = readInt("Nhap ID Department can sua: ");
        String name = readText("Nhap ten moi: ");
        if (DEPARTMENT.updateDepartmentNameById(id, name)) {
            System.out.println("Cap nhat Department thanh cong.");
        } else {
            System.out.println("Cap nhat that bai. Hay kiem tra ID va ten.");
        }
    }

    private static void printAccounts(List<Account> accounts) {
        System.out.printf("%-5s %-20s %-30s %-25s %-18s %-18s%n",
                "ID", "Username", "Email", "Full name", "Department", "Position");
        for (Account account : accounts) printAccount(account);
    }

    private static void printAccount(Account account) {
        String department = account.getDepartment() == null ? "" : account.getDepartment().getName();
        String position = account.getPosition() == null ? "" : account.getPosition().getName();
        System.out.printf("%-5d %-20s %-30s %-25s %-18s %-18s%n",
                account.getId(), account.getUsername(), account.getEmail(),
                account.getFullname(), department, position);
    }

    private static void printDepartments(List<Department> departments) {
        System.out.printf("%-5s %s%n", "ID", "Department name");
        for (Department department : departments) {
            System.out.printf("%-5d %s%n", department.getId(), department.getName());
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Hay nhap mot so nguyen.");
            }
        }
    }
}
