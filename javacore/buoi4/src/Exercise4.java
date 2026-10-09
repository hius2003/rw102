import java.util.Scanner;

public class Exercise4 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        question1();
        question2();
        question3();
        question4();
        question5();
        question6();
        question7();
        question8();
        question9();
        question10();
        question11();
        question12();
        question13();
    }

    public static void question1() {
        System.out.println("=== QUESTION 1 ===");
        System.out.print("Nhập vào một chuỗi: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Số lượng từ: 0");
        } else {
            String[] words = text.split("\\s+");
            System.out.println("Số lượng từ: " + words.length);
        }
    }

    public static void question2() {
        System.out.println("=== QUESTION 2 ===");
        System.out.print("Nhập xâu s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Nhập xâu s2: ");
        String s2 = scanner.nextLine();
        System.out.println("Kết quả: " + (s1 + s2));
    }

    public static void question3() {
        System.out.println("=== QUESTION 3 ===");
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        if (name.length() > 0) {
            String chuDau = name.substring(0, 1).toUpperCase();
            String chuSau = name.substring(1);
            System.out.println("Tên sau khi viết hoa chữ đầu: " + chuDau + chuSau);
        }
    }

    public static void question4() {
        System.out.println("=== QUESTION 4 ===");
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        for (int i = 0; i < name.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name.charAt(i));
        }
    }

    public static void question5() {
        System.out.println("=== QUESTION 5 ===");
        System.out.print("Nhập họ: ");
        String ho = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String ten = scanner.nextLine();
        System.out.println("Họ và tên đầy đủ: " + ho + " " + ten);
    }

    public static void question6() {
        System.out.println("=== QUESTION 6 ===");
        System.out.print("Nhập họ tên đầy đủ: ");
        String fullName = scanner.nextLine().trim();
        String[] words = fullName.split("\\s+");
        if (words.length >= 3) {
            System.out.println("Họ là: " + words[0]);
            System.out.println("Tên đệm là: " + words[1]);
            System.out.println("Tên là: " + words[2]);
        } else {
            System.out.println("Vui lòng nhập đủ 3 từ!");
        }
    }

    public static void question7() {
        System.out.println("=== QUESTION 7 ===");
        System.out.print("Nhập họ tên cần chuẩn hóa: ");
        String fullName = scanner.nextLine().trim();
        fullName = fullName.replaceAll("\\s+", " ");
        String[] words = fullName.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            String tu = words[i];
            String tuChuanHoa = tu.substring(0, 1).toUpperCase() + tu.substring(1).toLowerCase();
            result = result + tuChuanHoa + " ";
        }
        System.out.println("Kết quả chuẩn hóa: " + result.trim());
    }

    public static void question8() {
        System.out.println("=== QUESTION 8 ===");
        String[] groups = {"Java Basic", "Python", "Java Advanced", "C++"};
        System.out.println("Các group chứa chữ 'Java':");
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].contains("Java")) {
                System.out.println(groups[i]);
            }
        }
    }

    public static void question9() {
        System.out.println("=== QUESTION 9 ===");
        String[] groups = {"Java", "Python", "C++", "Java"};
        System.out.println("Các group đúng tên 'Java':");
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].equals("Java")) {
                System.out.println(groups[i]);
            }
        }
    }

    public static void question10() {
        System.out.println("=== QUESTION 10 ===");
        System.out.print("Nhập chuỗi 1: ");
        String str1 = scanner.nextLine();
        System.out.print("Nhập chuỗi 2: ");
        String str2 = scanner.nextLine();

        String daoNguoc = "";
        for (int i = str1.length() - 1; i >= 0; i--) {
            daoNguoc = daoNguoc + str1.charAt(i);
        }

        if (daoNguoc.equals(str2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }

    public static void question11() {
        System.out.println("=== QUESTION 11 ===");
        System.out.print("Nhập vào chuỗi: ");
        String str = scanner.nextLine();
        int dem = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                dem++;
            }
        }
        System.out.println("Số lần xuất hiện ký tự 'a': " + dem);
    }

    public static void question12() {
        System.out.println("=== QUESTION 12 ===");
        System.out.print("Nhập chuỗi cần đảo ngược: ");
        String str = scanner.nextLine();
        String daoNguoc = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            daoNguoc = daoNguoc + str.charAt(i);
        }
        System.out.println("Chuỗi đảo ngược: " + daoNguoc);
    }

    public static void question13() {
        System.out.println("=== QUESTION 13 ===");
        System.out.print("Nhập vào chuỗi: ");
        String str = scanner.nextLine();
        boolean khongChuaSo = true;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                khongChuaSo = false;
                break;
            }
        }
        System.out.println("Kết quả: " + khongChuaSo);
    }
}