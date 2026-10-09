package frontend;

import backend.IQLTV;
import backend.QLTV;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IQLTV quanLy = new QLTV(scanner);

        while (true) {
            System.out.println("\n===== QUẢN LÝ THƯ VIỆN =====");
            System.out.println("1. Thêm tài liệu");
            System.out.println("2. Xóa tài liệu theo mã");
            System.out.println("3. Hiển thị danh sách");
            System.out.println("4. Tìm kiếm theo loại");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            String luaChon = scanner.nextLine().trim();

            switch (luaChon) {
                case "1":
                    quanLy.themTaiLieu();
                    break;
                case "2":
                    quanLy.xoaTheoMa();
                    break;
                case "3":
                    quanLy.hienThiDanhSach();
                    break;
                case "4":
                    quanLy.timKiemTheoLoai();
                    break;
                case "0":
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 0 đến 4!");
            }
        }
    }
}