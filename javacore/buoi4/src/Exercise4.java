import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // QUESTION 1:
        System.out.println("QUESTION 1");
        System.out.print("Nhập vào một chuỗi: ");
        String text1 = scanner.nextLine().trim();
        if (text1.isEmpty()) {
            System.out.println("Số lượng từ: 0");
        } else {
            String[] words1 = text1.split("\\s+");
            System.out.println("Số lượng từ: " + words1.length);
        }

        // QUESTION 2: Nối xâu s2 vào sau xâu s1
        System.out.println("QUESTION 2");
        System.out.print("Nhập xâu s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Nhập xâu s2: ");
        String s2 = scanner.nextLine();
        System.out.println("Kết quả: " + (s1 + s2));

        // QUESTION 3:
        System.out.println("\nQUESTION 3");
        System.out.print("Nhập tên: ");
        String name3 = scanner.nextLine();
        if (name3.length() > 0) {
            // Lấy chữ đầu tiên viết hoa + ghép với các chữ còn lại
            String chuDau = name3.substring(0, 1).toUpperCase();
            String chuSau = name3.substring(1);
            System.out.println("Tên sau khi viết hoa chữ đầu: " + chuDau + chuSau);
        }

        // QUESTION 4: In ra từng ký tự trong tên

        System.out.println("\n=== QUESTION 4 ===");
        System.out.print("Nhập tên: ");
        String name4 = scanner.nextLine();
        for (int i = 0; i < name4.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name4.charAt(i));
        }

        // QUESTION 5: Nhập Họ, Tên -> In Họ và Tên
        System.out.println("\n=== QUESTION 5 ===");
        System.out.print("Nhập họ: ");
        String ho5 = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String ten5 = scanner.nextLine();
        System.out.println("Họ và tên đầy đủ: " + ho5 + " " + ten5);

        // QUESTION 6: Tách Họ, Tên đệm, Tên
        System.out.println("\n=== QUESTION 6 ===");
        System.out.print("Nhập họ tên đầy đủ (VD: Nguyễn Văn Nam): ");
        String fullName6 = scanner.nextLine().trim();
        String[] words6 = fullName6.split("\\s+");
        if (words6.length >= 3) {
            System.out.println("Họ là: " + words6[0]);
            System.out.println("Tên đệm là: " + words6[1]);
            System.out.println("Tên là: " + words6[2]);
        } else {
            System.out.println("Vui lòng nhập đủ 3 từ (Họ, Tên đệm, Tên)!");
        }

        // QUESTION 7: Chuẩn hóa họ tên
        System.out.println("\n=== QUESTION 7 ===");
        System.out.print("Nhập họ tên cần chuẩn hóa: ");
        String fullName7 = scanner.nextLine().trim();
        fullName7 = fullName7.replaceAll("\\s+", " ");
        String[] words7 = fullName7.split(" ");
        String result7 = "";
        for (int i = 0; i < words7.length; i++) {
            String tu = words7[i];
            String tuChuanHoa = tu.substring(0, 1).toUpperCase() + tu.substring(1).toLowerCase();
            result7 = result7 + tuChuanHoa + " ";
        }
        System.out.println("Kết quả chuẩn hóa: " + result7.trim());
        // QUESTION 8: In group chứa chữ "Java"
        System.out.println("\n=== QUESTION 8 ===");
        String[] groups8 = {"Java Basic", "Python", "Java Advanced", "C++"};
        System.out.println("Các group chứa chữ 'Java':");
        for (int i = 0; i < groups8.length; i++) {
            if (groups8[i].contains("Java")) {
                System.out.println(groups8[i]);
            }
        }

        // QUESTION 9: In group đúng bằng "Java"
        System.out.println("\n=== QUESTION 9 ===");
        String[] groups9 = {"Java", "Python", "C++", "Java"};
        System.out.println("Các group đúng tên 'Java':");
        for (int i = 0; i < groups9.length; i++) {
            if (groups9[i].equals("Java")) {
                System.out.println(groups9[i]);
            }
        }

        // QUESTION 10: Kiểm tra 2 chuỗi có đảo ngược của nhau
        System.out.println("\n=== QUESTION 10 ===");
        System.out.print("Nhập chuỗi 1: ");
        String str10a = scanner.nextLine();
        System.out.print("Nhập chuỗi 2: ");
        String str10b = scanner.nextLine();

        String daoNguoc = "";
        for (int i = str10a.length() - 1; i >= 0; i--) {
            daoNguoc = daoNguoc + str10a.charAt(i);
        }

        if (daoNguoc.equals(str10b)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }


        // QUESTION 11
        System.out.println("\n=== QUESTION 11 ===");
        System.out.print("Nhập vào chuỗi: ");
        String str11 = scanner.nextLine();
        int dem11 = 0;
        for (int i = 0; i < str11.length(); i++) {
            if (str11.charAt(i) == 'a') {
                dem11 = dem11 + 1;
            }
        }
        System.out.println("Số lần xuất hiện ký tự 'a': " + dem11);

        // QUESTION 12: Đảo ngược chuỗi dùng vòng lặp
        System.out.println("\n=== QUESTION 12 ===");
        System.out.print("Nhập chuỗi cần đảo ngược: ");
        String str12 = scanner.nextLine();
        String str12DaoNguoc = "";
        for (int i = str12.length() - 1; i >= 0; i--) {
            str12DaoNguoc = str12DaoNguoc + str12.charAt(i);
        }
        System.out.println("Chuỗi đảo ngược: " + str12DaoNguoc);


        // QUESTION 13: Kiểm tra chuỗi có chứa chữ số không
        System.out.println("\nQUESTION 13");
        System.out.print("Nhập vào chuỗi: ");
        String str13 = scanner.nextLine();
        boolean khongChuaSo = true;
        for (int i = 0; i < str13.length(); i++) {
            if (Character.isDigit(str13.charAt(i))) {
                khongChuaSo = false;
                break;
            }
        }
        System.out.println("Kết quả: " + khongChuaSo);

        scanner.close();
    }
}