package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class QLTV implements IQLTV {
    private ArrayList<TaiLieu> danhSach = new ArrayList<>();
    private Scanner scanner;

    public QLTV(Scanner scanner) {
        this.scanner = scanner;
    }

    // 1. Thêm tài liệu
    @Override
    public void themTaiLieu() {
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");

        System.out.print("Chọn loại: ");
        int loai = Integer.parseInt(scanner.nextLine());

        if (loai < 1 || loai > 3) {
            System.out.println("Loại không hợp lệ!");
            return;
        }

        System.out.print("Mã tài liệu: ");
        String ma = scanner.nextLine();

        // Kiểm tra mã trùng
        for (TaiLieu taiLieu : danhSach) {
            if (taiLieu.getMaTaiLieu().equalsIgnoreCase(ma)) {
                System.out.println("Mã đã tồn tại!");
                return;
            }
        }

        System.out.print("Tên nhà xuất bản: ");
        String nhaXuatBan = scanner.nextLine();

        System.out.print("Số bản phát hành: ");
        int soBan = Integer.parseInt(scanner.nextLine());

        switch (loai) {
            case 1:
                System.out.print("Tên tác giả: ");
                String tacGia = scanner.nextLine();

                System.out.print("Số trang: ");
                int soTrang = Integer.parseInt(scanner.nextLine());

                Sach sach = new Sach(
                        ma, nhaXuatBan, soBan, tacGia, soTrang
                );

                danhSach.add(sach);
                break;

            case 2:
                System.out.print("Số phát hành: ");
                int soPhatHanh = Integer.parseInt(scanner.nextLine());

                System.out.print("Tháng phát hành: ");
                int thang = Integer.parseInt(scanner.nextLine());

                TapChi tapChi = new TapChi(
                        ma, nhaXuatBan, soBan, soPhatHanh, thang
                );

                danhSach.add(tapChi);
                break;

            case 3:
                System.out.print("Ngày phát hành (yyyy-MM-dd): ");
                LocalDate ngay = LocalDate.parse(scanner.nextLine());

                Bao bao = new Bao(ma, nhaXuatBan, soBan, ngay);

                danhSach.add(bao);
                break;
        }

        System.out.println("Thêm thành công!");
    }

    // 2. Xóa tài liệu theo mã
    @Override
    public void xoaTheoMa() {
        System.out.print("Nhập mã cần xóa: ");
        String ma = scanner.nextLine();

        for (int i = 0; i < danhSach.size(); i++) {
            TaiLieu taiLieu = danhSach.get(i);

            if (taiLieu.getMaTaiLieu().equalsIgnoreCase(ma)) {
                danhSach.remove(i);
                System.out.println("Xóa thành công!");
                return;
            }
        }

        System.out.println("Không tìm thấy tài liệu!");
    }

    // 3. Hiển thị tất cả tài liệu
    @Override
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách đang trống.");
            return;
        }

        for (TaiLieu taiLieu : danhSach) {
            System.out.println(taiLieu);
        }
    }

    // 4. Tìm tài liệu theo loại
    @Override
    public void timKiemTheoLoai() {
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");

        System.out.print("Chọn loại cần tìm: ");
        int loai = Integer.parseInt(scanner.nextLine());

        if (loai < 1 || loai > 3) {
            System.out.println("Loại không hợp lệ!");
            return;
        }

        boolean timThay = false;

        for (TaiLieu taiLieu : danhSach) {
            switch (loai) {
                case 1:
                    if (taiLieu instanceof Sach) {
                        System.out.println(taiLieu);
                        timThay = true;
                    }
                    break;

                case 2:
                    if (taiLieu instanceof TapChi) {
                        System.out.println(taiLieu);
                        timThay = true;
                    }
                    break;

                case 3:
                    if (taiLieu instanceof Bao) {
                        System.out.println(taiLieu);
                        timThay = true;
                    }
                    break;
            }
        }

        if (timThay == false) {
            System.out.println("Không có tài liệu thuộc loại này.");
        }
    }
}