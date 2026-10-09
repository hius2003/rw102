import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Exercise4 {
    public static void main(String[] args) {
        Random random = new Random();

        // Question 1:
        System.out.println("QUESTION 1:");
        int randomInt = random.nextInt();
        System.out.println("Số nguyên ngẫu nhiên: " + randomInt);

        // Question 2:
        System.out.println("QUESTION 2:");
        float randomFloat = random.nextFloat();
        System.out.println("Số thực ngẫu nhiên: " + randomFloat);

        // Question 3:
        System.out.println("QUESTION 3:");
        String[] names = { "Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Văn D" };
        String randomName = names[random.nextInt(names.length)];
        System.out.println("Tên ngẫu nhiên: " + randomName);

        // Question 4:
        System.out.println("QUESTION 4:");
        long minDay1 = LocalDate.of(1995, 7, 24).toEpochDay();
        long maxDay1 = LocalDate.of(1995, 12, 20).toEpochDay();
        long randomDay1 = ThreadLocalRandom.current().nextLong(minDay1, maxDay1 + 1);
        System.out.println("Ngày ngẫu nhiên: " + LocalDate.ofEpochDay(randomDay1));

        // Question 5:
        System.out.println("QUESTION 5:");
        long minDay2 = LocalDate.now().minusYears(1).toEpochDay();
        long maxDay2 = LocalDate.now().toEpochDay();
        long randomDay2 = ThreadLocalRandom.current().nextLong(minDay2, maxDay2 + 1);
        System.out.println("Ngày ngẫu nhiên (1 năm trở lại đây): " + LocalDate.ofEpochDay(randomDay2));

        // Question 6:
        System.out.println("QUESTION 6:");
        long minDay3 = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay3 = LocalDate.now().minusDays(1).toEpochDay();
        long randomDay3 = ThreadLocalRandom.current().nextLong(minDay3, maxDay3 + 1);
        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + LocalDate.ofEpochDay(randomDay3));

        // Question 7:
        System.out.println("QUESTION 7:");
        int random3Digit = random.nextInt(900) + 100;
        System.out.println("Số ngẫu nhiên 3 chữ số: " + random3Digit);
    }
}