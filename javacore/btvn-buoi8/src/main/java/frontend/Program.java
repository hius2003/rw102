package frontend;

import backend.QLAccount;
import backend.QLDepartment;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);
    private static final QLAccount accountService = new QLAccount();
    private static final QLDepartment departmentService = new QLDepartment();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== QUẢN LÝ CÔNG TY =====");
            System.out.println("1. Quản lý Account");
            System.out.println("2. Quản lý Department");
            System.out.println("0. Thoát");
            int choice = readInt("Chọn: ");
            if (choice == 0) break;
            if (choice == 1) accountMenu();
            else if (choice == 2) departmentMenu();
            else System.out.println("Lựa chọn không hợp lệ.");
        }
        scanner.close();
    }

    private static void accountMenu() {
        while (true) {
            System.out.println("\n--- ACCOUNT ---");
            System.out.println("1. Hiển thị tất cả account");
            System.out.println("2. Tìm theo username");
            System.out.println("3. Thêm account");
            System.out.println("4. Xóa theo username");
            System.out.println("5. Sửa full name theo username");
            System.out.println("0. Quay lại");
            int choice = readInt("Chọn: ");
            switch (choice) {
                case 1 -> printAccounts(accountService.getAll());
                case 2 -> {
                    Account account = accountService.findByUsername(readText("Nhập username: "));
                    if (account == null) System.out.println("Không tìm thấy account.");
                    else printAccount(account);
                }
                case 3 -> addAccount();
                case 4 -> showResult(accountService.deleteByUsername(readText("Username cần xóa: ")));
                case 5 -> {
                    String username = readText("Username cần sửa: ");
                    String fullName = readText("Tên mới: ");
                    showResult(accountService.updateFullName(username, fullName));
                }
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addAccount() {
        String username = readText("Username: ");
        String fullName = readText("Full name: ");
        String email = readText("Email: ");
        printDepartments(departmentService.getAll());
        int departmentId = readInt("Department id: ");
        System.out.println("Position id: 1=DEV, 2=TEST, 3=PM");
        int positionId = readInt("Position id: ");
        Account account = new Account(0, username, fullName, email,
                new Department(departmentId, ""), new Position(positionId, ""));
        showResult(accountService.add(account));
    }

    private static void departmentMenu() {
        while (true) {
            System.out.println("\n--- DEPARTMENT ---");
            System.out.println("1. Hiển thị tất cả department");
            System.out.println("2. Tìm theo tên");
            System.out.println("3. Thêm department");
            System.out.println("4. Xóa theo id");
            System.out.println("5. Sửa tên theo id");
            System.out.println("0. Quay lại");
            int choice = readInt("Chọn: ");
            switch (choice) {
                case 1 -> printDepartments(departmentService.getAll());
                case 2 -> printDepartments(departmentService.findByName(readText("Tên cần tìm: ")));
                case 3 -> showResult(departmentService.add(readText("Tên department mới: ")));
                case 4 -> showResult(departmentService.deleteById(readInt("Id cần xóa: ")));
                case 5 -> {
                    int id = readInt("Id cần sửa: ");
                    String name = readText("Tên mới: ");
                    showResult(departmentService.updateName(id, name));
                }
                case 0 -> { return; }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(readText(message));
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên.");
            }
        }
    }

    private static void printAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("Danh sách account đang trống.");
            return;
        }
        System.out.printf("%-4s %-15s %-22s %-28s %-15s %-10s%n",
                "ID", "Username", "Full name", "Email", "Department", "Position");
        for (Account account : accounts) printAccount(account);
    }

    private static void printAccount(Account account) {
        System.out.printf("%-4d %-15s %-22s %-28s %-15s %-10s%n",
                account.getId(), account.getUsername(), account.getFullName(), account.getEmail(),
                account.getDepartment().getName(), account.getPosition().getName());
    }

    private static void printDepartments(List<Department> departments) {
        if (departments.isEmpty()) {
            System.out.println("Không có department nào.");
            return;
        }
        System.out.printf("%-5s %s%n", "ID", "Department name");
        for (Department department : departments) {
            System.out.printf("%-5d %s%n", department.getId(), department.getName());
        }
    }

    private static void showResult(boolean success) {
        System.out.println(success ? "Thao tác thành công." : "Thao tác thất bại hoặc không tìm thấy dữ liệu.");
    }
}
