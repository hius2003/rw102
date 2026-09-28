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

    @Override
    public void themTaiLieu() {
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");
        System.out.print("Chọn loại: ");
        String loai = scanner.nextLine();

        if (!loai.equals("1")
                && !loai.equals("2")
                && !loai.equals("3")) {
            System.out.println("Loại không hợp lệ!");
            return;
        }

        System.out.print("Mã tài liệu: ");
        String ma = scanner.nextLine();

        // Kiểm tra mã đã tồn tại chưa
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

        if (loai.equals("1")) {
            System.out.print("Tên tác giả: ");
            String tacGia = scanner.nextLine();

            System.out.print("Số trang: ");
            int soTrang = Integer.parseInt(scanner.nextLine());

            Sach sach = new Sach(
                    ma, nhaXuatBan, soBan, tacGia, soTrang
            );

            danhSach.add(sach);

        } else if (loai.equals("2")) {
            System.out.print("Số phát hành: ");
            int soPhatHanh = Integer.parseInt(scanner.nextLine());

            System.out.print("Tháng phát hành: ");
            int thang = Integer.parseInt(scanner.nextLine());

            TapChi tapChi = new TapChi(
                    ma, nhaXuatBan, soBan, soPhatHanh, thang
            );

            danhSach.add(tapChi);

        } else {
            System.out.print("Ngày phát hành (yyyy-MM-dd): ");
            LocalDate ngay = LocalDate.parse(scanner.nextLine());

            Bao bao = new Bao(ma, nhaXuatBan, soBan, ngay);

            danhSach.add(bao);
        }

        System.out.println("Thêm thành công!");
    }

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

        System.out.println("Không tìm thấy mã tài liệu!");
    }

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

    @Override
    public void timKiemTheoLoai() {
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");
        System.out.print("Chọn loại cần tìm: ");
        String loai = scanner.nextLine();

        boolean timThay = false;

        for (TaiLieu taiLieu : danhSach) {
            if (loai.equals("1") && taiLieu instanceof Sach) {
                System.out.println(taiLieu);
                timThay = true;
            } else if (loai.equals("2") && taiLieu instanceof TapChi) {
                System.out.println(taiLieu);
                timThay = true;
            } else if (loai.equals("3") && taiLieu instanceof Bao) {
                System.out.println(taiLieu);
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy tài liệu phù hợp.");
        }
    }
}