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

    private final Scanner scanner = new Scanner(System.in);
    private final AccountController accountController =
            new AccountController();
    private final DepartmentController departmentController =
            new DepartmentController();

    public static void main(String[] args) {
        new Program().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý Account");
            System.out.println("2. Quản lý Department");
            System.out.println("0. Thoát");

            int choice = readInt("Chọn chức năng: ");

            try {
                switch (choice) {
                    case 1:
                        accountMenu();
                        break;
                    case 2:
                        departmentMenu();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Đã thoát chương trình.");
                        break;
                    default:
                        System.out.println("Chức năng không hợp lệ.");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi database: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void accountMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("\n========== QUẢN LÝ ACCOUNT ==========");
            System.out.println("1. Hiển thị tất cả Account");
            System.out.println("2. Tìm Account theo username");
            System.out.println("3. Thêm Account");
            System.out.println("4. Xóa Account theo ID");
            System.out.println("5. Sửa username theo ID");
            System.out.println("0. Quay lại");

            int choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    showAllAccounts();
                    break;
                case 2:
                    searchAccount();
                    break;
                case 3:
                    addAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    updateUsername();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        }
    }

    private void showAllAccounts() throws SQLException {
        List<Account> accounts = accountController.getAllAccounts();

        if (accounts.isEmpty()) {
            System.out.println("Chưa có Account nào.");
            return;
        }

        printAccountHeader();

        for (Account account : accounts) {
            printAccount(account);
        }
    }

    private void searchAccount() throws SQLException {
        String username = readText("Nhập username cần tìm: ");

        Account account =
                accountController.findAccountByUsername(username);

        if (account == null) {
            System.out.println("Không tìm thấy Account.");
        } else {
            printAccountHeader();
            printAccount(account);
        }
    }

    private void addAccount() throws SQLException {
        List<Department> departments = departmentController.getAllDepartments();
        List<Position> positions = accountController.getAllPositions();

        if (departments.isEmpty() || positions.isEmpty()) {
            System.out.println("Cần có Department và Position trong database trước.");
            return;
        }

        String username;
        while (true) {
            username = readText("Username (5-50 ký tự): ");
            if (username.length() < 5 || username.length() > 50) {
                System.out.println("Username phải dài từ 5 đến 50 ký tự.");
            } else if (!accountController.isValidUsername(username)) {
                System.out.println("Username đã tồn tại. Hãy nhập lại.");
            } else {
                break;
            }
        }

        String email;
        while (true) {
            email = readText("Email (5-50 ký tự): ");
            if (email.length() < 5 || email.length() > 50) {
                System.out.println("Email phải dài từ 5 đến 50 ký tự.");
            } else if (!accountController.isValidEmail(email)) {
                System.out.println("Email sai định dạng hoặc đã tồn tại. Hãy nhập lại.");
            } else {
                break;
            }
        }

        String fullname;
        while (true) {
            fullname = readText("Fullname (5-50 ký tự): ");
            if (fullname.length() >= 5 && fullname.length() <= 50) {
                break;
            }
            System.out.println("Fullname phải dài từ 5 đến 50 ký tự.");
        }

        System.out.println("\nDanh sách Department:");
        showDepartments(departments);
        int departmentId;
        Department selectedDepartment;
        while (true) {
            departmentId = readInt("Nhập Department ID: ");
            selectedDepartment = findDepartment(departments, departmentId);
            if (selectedDepartment != null) {
                break;
            }
            System.out.println("ID không tồn tại. Hãy nhập lại.");
        }

        System.out.println("\nDanh sách Position:");
        showPositions(positions);
        int positionId;
        Position selectedPosition;
        while (true) {
            positionId = readInt("Nhập Position ID: ");
            selectedPosition = findPosition(positions, positionId);
            if (selectedPosition != null) {
                break;
            }
            System.out.println("ID không tồn tại. Hãy nhập lại.");
        }

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setFullName(fullname);
        account.setDepartment(selectedDepartment);
        account.setPosition(selectedPosition);

        if (accountController.addAccount(account)) {
            System.out.println("Thêm Account thành công. ID do database tự sinh.");
        } else {
            System.out.println("Thêm thất bại. Hãy kiểm tra dữ liệu.");
        }
    }

    private void deleteAccount() throws SQLException {
        while (true) {
            int id = readInt("Nhập Account ID cần xóa: ");
            if (!accountController.isAccountIdExists(id)) {
                System.out.println("ID không tồn tại. Hãy nhập lại.");
                continue;
            }
            if (accountController.deleteAccountById(id)) {
                System.out.println("Xóa Account thành công.");
                break;
            }
            System.out.println("Xóa thất bại. Hãy thử lại.");
        }
    }

    private void updateUsername() throws SQLException {
        int id;
        while (true) {
            id = readInt("Nhập Account ID cần sửa: ");
            if (accountController.isAccountIdExists(id)) {
                break;
            }
            System.out.println("ID không tồn tại. Hãy nhập lại.");
        }

        while (true) {
            String username = readText("Nhập username mới (5-50 ký tự): ");
            if (username.length() < 5 || username.length() > 50) {
                System.out.println("Username phải dài từ 5 đến 50 ký tự.");
                continue;
            }
            if (accountController.updateUsernameById(id, username)) {
                System.out.println("Cập nhật username thành công.");
                break;
            }
            System.out.println("Username đã được Account khác sử dụng. Hãy nhập lại.");
        }
    }

    private void departmentMenu() throws SQLException {
        boolean running = true;

        while (running) {
            System.out.println("\n========== QUẢN LÝ DEPARTMENT ==========");
            System.out.println("1. Hiển thị Department");
            System.out.println("2. Tìm Department theo tên");
            System.out.println("3. Thêm Department");
            System.out.println("4. Xóa Department theo ID");
            System.out.println("5. Sửa tên Department theo ID");
            System.out.println("0. Quay lại");

            int choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    showDepartments();
                    break;
                case 2:
                    searchDepartment();
                    break;
                case 3:
                    addDepartment();
                    break;
                case 4:
                    deleteDepartment();
                    break;
                case 5:
                    updateDepartment();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        }
    }

    private void showDepartments() throws SQLException {
        List<Department> departments =
                departmentController.getAllDepartments();

        if (departments.isEmpty()) {
            System.out.println("Chưa có Department nào.");
            return;
        }

        System.out.printf("%-5s %-30s%n", "ID", "Name");

        for (Department department : departments) {
            System.out.printf(
                    "%-5d %-30s%n",
                    department.getId(),
                    department.getName()
            );
        }
    }

    private void searchDepartment() throws SQLException {
        String name = readText("Nhập tên hoặc một phần tên cần tìm: ");

        List<Department> departments =
                departmentController.searchDepartmentByName(name);

        if (departments.isEmpty()) {
            System.out.println("Không tìm thấy Department.");
            return;
        }

        System.out.printf("%-5s %-30s%n", "ID", "Name");

        for (Department department : departments) {
            System.out.printf(
                    "%-5d %-30s%n",
                    department.getId(),
                    department.getName()
            );
        }
    }

    private void addDepartment() throws SQLException {
        String name = readText("Nhập tên Department mới: ");

        boolean added = departmentController.addDepartment(name);

        if (added) {
            System.out.println("Thêm Department thành công.");
        } else {
            System.out.println("Không thêm được Department.");
        }
    }

    private void deleteDepartment() throws SQLException {
        int id = readInt("Nhập ID Department cần xóa: ");

        boolean deleted = departmentController.deleteDepartmentById(id);

        if (deleted) {
            System.out.println("Xóa Department thành công.");
            System.out.println("Account cũ vẫn còn, nhưng mất Department.");
        } else {
            System.out.println("Không tìm thấy Department có ID này.");
        }
    }

    private void updateDepartment() throws SQLException {
        int id = readInt("Nhập ID Department cần sửa: ");
        String name = readText("Nhập tên mới: ");

        boolean updated =
                departmentController.updateDepartmentNameById(id, name);

        if (updated) {
            System.out.println("Cập nhật tên Department thành công.");
        } else {
            System.out.println("Không tìm thấy Department có ID này.");
        }
    }

    private void printAccountHeader() {
        System.out.printf(
                "%-5s %-18s %-28s %-20s %-18s %-18s%n",
                "ID", "Username", "Email", "Fullname", "Position", "Department"
        );
    }

    private void printAccount(Account account) {
        String positionName = "-";
        String departmentName = "-";

        if (account.getPosition() != null) {
            positionName = account.getPosition().getName();
        }

        if (account.getDepartment() != null) {
            departmentName = account.getDepartment().getName();
        }

        System.out.printf(
                "%-5d %-18s %-28s %-20s %-18s %-18s%n",
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getFullName(),
                positionName,
                departmentName
        );
    }

    private void showDepartments(List<Department> departments) {
        System.out.printf("%-5s %-25s%n", "ID", "Name");
        for (Department department : departments) {
            System.out.printf("%-5d %-25s%n", department.getId(), department.getName());
        }
    }

    private void showPositions(List<Position> positions) {
        System.out.printf("%-5s %-25s%n", "ID", "Name");
        for (Position position : positions) {
            System.out.printf("%-5d %-25s%n", position.getId(), position.getName());
        }
    }

    private Department findDepartment(List<Department> departments, int id) {
        for (Department department : departments) {
            if (department.getId() == id) {
                return department;
            }
        }
        return null;
    }

    private Position findPosition(List<Position> positions, int id) {
        for (Position position : positions) {
            if (position.getId() == id) {
                return position;
            }
        }
        return null;
    }

    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên.");
            }
        }
    }
}