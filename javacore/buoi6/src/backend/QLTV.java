package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV {
    private List<TaiLieu> taiLieus;
    private Scanner scanner;

    public QLTV(Scanner scanner) {
        this.scanner = scanner;
        this.taiLieus = new ArrayList<>();

        // Khởi tạo các giá trị mẫu
        taiLieus.add(new Bao("bao1", "NXB1", 100, LocalDate.of(2020, 1, 1)));
        taiLieus.add(new Sach("sach1", "NXB2", 200, "ABC", 55));
        taiLieus.add(new TapChi("tapchi1", "NXB3", 300, 1, 1));
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
        for (TaiLieu taiLieu : taiLieus) {
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

                TaiLieu sach = new Sach(ma, nhaXuatBan, soBan, tacGia, soTrang);
                taiLieus.add(sach);
                System.out.println("Thêm sách thành công!");
                break;

            case 2:
                System.out.print("Số phát hành: ");
                int soPhatHanh = Integer.parseInt(scanner.nextLine());

                System.out.print("Tháng phát hành: ");
                int thang = Integer.parseInt(scanner.nextLine());

                TaiLieu tapChi = new TapChi(ma, nhaXuatBan, soBan, soPhatHanh, thang);
                taiLieus.add(tapChi);
                System.out.println("Thêm tạp chí thành công!");
                break;

            case 3:
                System.out.print("Ngày phát hành (yyyy-MM-dd): ");
                LocalDate ngay = LocalDate.parse(scanner.nextLine());

                TaiLieu bao = new Bao(ma, nhaXuatBan, soBan, ngay);
                taiLieus.add(bao);
                System.out.println("Thêm báo thành công!");
                break;

        }
    }

    // 2. Xóa tài liệu theo mã
    @Override
    public void xoaTheoMa() {
        System.out.print("Nhập mã cần xóa: ");
        String ma = scanner.nextLine();

        boolean rs = taiLieus.removeIf(taiLieu -> taiLieu.getMaTaiLieu().equalsIgnoreCase(ma));

        if (rs) {
            System.out.println("Xoá thành công!");
        } else {
            System.out.println("Không có dữ liệu tương ứng để xoá!");
        }
    }

    // 3. Hiển thị tất cả tài liệu
    @Override
    public void hienThiDanhSach() {
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        System.out.printf("|%25s|%25s|%25s|\n", "Mã tài liệu", "Tên NXB", "Số bản phát hành");
        System.out.println("+-------------------------+-------------------------+-------------------------+");
        if (taiLieus.size() > 0) {
            for (TaiLieu taiLieu : taiLieus) {
                System.out.printf("|%25s|%25s|%25s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXuatBan(), taiLieu.getSoBanPhatHanh());
            }
        } else {
            System.out.printf("|%77s|\n", "Không có thông tin");
        }
        System.out.println("+-------------------------+-------------------------+-------------------------+");
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

        for (TaiLieu taiLieu : taiLieus) {
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

        if (!timThay) {
            System.out.println("Không có tài liệu thuộc loại này.");
        }
    }
}