import java.time.LocalDate;
import java.util.Random;

public class Exercise4 {
    public static void main(String[] args) {
        Random random = new Random();

        // Question 1: In ngẫu nhiên 1 số nguyên
        System.out.println("QUESTION 1:");
        int randomInt = random.nextInt();
        System.out.println("Số nguyên ngẫu nhiên: " + randomInt);

        // Question 2: In ngẫu nhiên 1 số thực (từ 0.0 đến 1.0)
        System.out.println("QUESTION 2:");
        float randomFloat = random.nextFloat();
        System.out.println("Số thực ngẫu nhiên: " + randomFloat);

        // Question 3: Lấy ngẫu nhiên 1 tên trong mảng
        System.out.println("QUESTION 3:");
        String[] names = { "Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Văn D" };
        int index = random.nextInt(names.length); // Lấy vị trí ngẫu nhiên từ 0 đến names.length - 1
        System.out.println("Tên ngẫu nhiên: " + names[index]);

        // Question 4: Ngày ngẫu nhiên từ 24/07/1995 đến 20/12/1995
        System.out.println("QUESTION 4:");
        int minDay = (int) LocalDate.of(1995, 7, 24).toEpochDay();
        int maxDay = (int) LocalDate.of(1995, 12, 20).toEpochDay();
        int randomDay = minDay + random.nextInt(maxDay - minDay + 1);
        System.out.println("Ngày ngẫu nhiên: " + LocalDate.ofEpochDay(randomDay));

        // Question 5: Ngày ngẫu nhiên trong 1 năm trở lại đây (lùi lại từ 0 đến 365 ngày)
        System.out.println("QUESTION 5:");
        int backDays = random.nextInt(365);
        LocalDate randomDate1Year = LocalDate.now().minusDays(backDays);
        System.out.println("Ngày ngẫu nhiên trong 1 năm qua: " + randomDate1Year);

        // Question 6: Ngày ngẫu nhiên trong quá khứ (lùi lại ngẫu nhiên từ 1 đến 10000 ngày)
        System.out.println("QUESTION 6:");
        int pastDays = random.nextInt(10000) + 1;
        LocalDate randomPastDate = LocalDate.now().minusDays(pastDays);
        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + randomPastDate);

        // Question 7: Số ngẫu nhiên có 3 chữ số (từ 100 đến 999)
        System.out.println("QUESTION 7:");
        int random3Digit = random.nextInt(900) + 100; // nextInt(900) sinh ra 0 -> 899, cộng 100 ra 100 -> 999
        System.out.println("Số có 3 chữ số: " + random3Digit);
    }
}