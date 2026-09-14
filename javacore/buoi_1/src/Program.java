import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // 1. Department
        Department dept1 = new Department();
        dept1.id = 1;
        dept1.name = "Sale";

        Department dept2 = new Department();
        dept2.id = 2;
        dept2.name = "Marketing";

        Department dept3 = new Department();
        dept3.id = 3;
        dept3.name = "Dev";

        // 2. Position
        Position pos1 = new Position();
        pos1.id = 1;
        pos1.name = "Dev";

        Position pos2 = new Position();
        pos2.id = 2;
        pos2.name = "Test";

        Position pos3 = new Position();
        pos3.id = 3;
        pos3.name = "PM";

        // 3. Account
        Account acc1 = new Account();
        acc1.id = 1;
        acc1.email = "trunheos@gmail.com";
        acc1.username = "hieutm";
        acc1.fullName = "Mai Trung Hieu";
        acc1.department = dept3;
        acc1.position = pos1;
        acc1.createDate = LocalDate.now();

        Account acc2 = new Account();
        acc2.id = 2;
        acc2.email = "ngobakha@gmail.com";
        acc2.username = "khabanh";
        acc2.fullName = "Ngo Ba Kha";
        acc2.department = dept1;
        acc2.position = pos2;
        acc2.createDate = LocalDate.now();

        Account acc3 = new Account();
        acc3.id = 3;
        acc3.email = "huanhoahong@gmail.com";
        acc3.username = "huanrose";
        acc3.fullName = "Mai Trung Huan";
        acc3.department = dept2;
        acc3.position = pos3;
        acc3.createDate = LocalDate.now();

        // 4. Group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Java Creator";
        group1.creator = acc1;
        group1.createDate = LocalDate.now();

        Group group2 = new Group();
        group2.id = 2;
        group2.name = "C++ Developer";
        group2.creator = acc2;
        group2.createDate = LocalDate.now();

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Python Club";
        group3.creator = acc3;
        group3.createDate = LocalDate.now();

        // 5. Group_account
        GroupAccount ga1 = new GroupAccount();
        ga1.group = group1;
        ga1.account = acc1;
        ga1.joinDate = LocalDate.now();

        GroupAccount ga2 = new GroupAccount();
        ga2.group = group2;
        ga2.account = acc2;
        ga2.joinDate = LocalDate.now();

        GroupAccount ga3 = new GroupAccount();
        ga3.group = group3;
        ga3.account = acc3;
        ga3.joinDate = LocalDate.now();

        // 6. Type_question
        TypeQuestion type1 = new TypeQuestion();
        type1.id = 1;
        type1.name = "Tự luận";

        TypeQuestion type2 = new TypeQuestion();
        type2.id = 2;
        type2.name = "Trắc nghiệm";

        TypeQuestion type3 = new TypeQuestion();
        type3.id = 3;
        type3.name = "True/False";

        // 7. Category_question
        CategoryQuestion cat1 = new CategoryQuestion();
        cat1.id = 1;
        cat1.name = "Java";

        CategoryQuestion cat2 = new CategoryQuestion();
        cat2.id = 2;
        cat2.name = "SQL";

        CategoryQuestion cat3 = new CategoryQuestion();
        cat3.id = 3;
        cat3.name = "Python";

        // 8. Question
        Question q1 = new Question();
        q1.id = 1;
        q1.content = "Java la gi?";
        q1.category = cat1;
        q1.type = type1;
        q1.creator = acc1;
        q1.createDate = LocalDate.now();

        Question q2 = new Question();
        q2.id = 2;
        q2.content = "SQL dung de lam gi?";
        q2.category = cat2;
        q2.type = type2;
        q2.creator = acc2;
        q2.createDate = LocalDate.now();

        Question q3 = new Question();
        q3.id = 3;
        q3.content = "Python co de hoc khong?";
        q3.category = cat3;
        q3.type = type1;
        q3.creator = acc3;
        q3.createDate = LocalDate.now();

        // 9. Answer
        Answer ans1 = new Answer();
        ans1.id = 1;
        ans1.content = "Java la ngon ngu lap trinh";
        ans1.question = q1;
        ans1.isCorrect = true;

        Answer ans2 = new Answer();
        ans2.id = 2;
        ans2.content = "SQL dung de quan ly CSDL";
        ans2.question = q2;
        ans2.isCorrect = true;

        Answer ans3 = new Answer();
        ans3.id = 3;
        ans3.content = "Python rat de hoc";
        ans3.question = q3;
        ans3.isCorrect = false;

        // 10. Exam
        Exam exam1 = new Exam();
        exam1.id = 1;
        exam1.code = "VTI001";
        exam1.title = "De thi Java";
        exam1.category = cat1;
        exam1.duration = 60;
        exam1.creator = acc1;
        exam1.createDate = LocalDate.now();

        Exam exam2 = new Exam();
        exam2.id = 2;
        exam2.code = "VTI002";
        exam2.title = "De thi SQL";
        exam2.category = cat2;
        exam2.duration = 90;
        exam2.creator = acc2;
        exam2.createDate = LocalDate.now();

        Exam exam3 = new Exam();
        exam3.id = 3;
        exam3.code = "VTI003";
        exam3.title = "De thi Python";
        exam3.category = cat3;
        exam3.duration = 45;
        exam3.creator = acc3;
        exam3.createDate = LocalDate.now();

        // 11. Exam_question
        ExamQuestion eq1 = new ExamQuestion();
        eq1.exam = exam1;
        eq1.question = q1;

        ExamQuestion eq2 = new ExamQuestion();
        eq2.exam = exam2;
        eq2.question = q2;

        ExamQuestion eq3 = new ExamQuestion();
        eq3.exam = exam3;
        eq3.question = q3;


        System.out.println("--- THONG TIN DEPARTMENT ---");
        System.out.println("Dept 1 Name: " + dept1.name);
        System.out.println("Dept 2 Name: " + dept2.name);
        System.out.println("Dept 3 Name: " + dept3.name);

        System.out.println("\n--- THONG TIN POSITION ---");
        System.out.println("Position 1: " + pos1.name);
        System.out.println("Position 2: " + pos2.name);
        System.out.println("Position 3: " + pos3.name);

        System.out.println("\n--- THONG TIN ACCOUNT ---");
        System.out.println("Acc 1 FullName: " + acc1.fullName + " | Phong ban: " + acc1.department.name);
        System.out.println("Acc 2 FullName: " + acc2.fullName + " | Phong ban: " + acc2.department.name);
        System.out.println("Acc 3 FullName: " + acc3.fullName + " | Phong ban: " + acc3.department.name);

        System.out.println("\n--- THONG TIN GROUP ---");
        System.out.println("Group 1 Name: " + group1.name + " | Nguoi tao: " + group1.creator.fullName);
        System.out.println("Group 2 Name: " + group2.name + " | Nguoi tao: " + group2.creator.fullName);
        System.out.println("Group 3 Name: " + group3.name + " | Nguoi tao: " + group3.creator.fullName);

        System.out.println("\n--- THONG TIN GROUP ACCOUNT ---");
        System.out.println("GA 1 Group: " + ga1.group.name + " | User: " + ga1.account.fullName);
        System.out.println("GA 2 Group: " + ga2.group.name + " | User: " + ga2.account.fullName);
        System.out.println("GA 3 Group: " + ga3.group.name + " | User: " + ga3.account.fullName);

        System.out.println("\n--- THONG TIN TYPE QUESTION ---");
        System.out.println("Type 1: " + type1.name);
        System.out.println("Type 2: " + type2.name);
        System.out.println("Type 3: " + type3.name);

        System.out.println("\n--- THONG TIN CATEGORY QUESTION ---");
        System.out.println("Category 1: " + cat1.name);
        System.out.println("Category 2: " + cat2.name);
        System.out.println("Category 3: " + cat3.name);

        System.out.println("\n--- THONG TIN QUESTION ---");
        System.out.println("Q1 Content: " + q1.content + " | Chu de: " + q1.category.name);
        System.out.println("Q2 Content: " + q2.content + " | Chu de: " + q2.category.name);
        System.out.println("Q3 Content: " + q3.content + " | Chu de: " + q3.category.name);

        System.out.println("\n--- THONG TIN ANSWER ---");
        System.out.println("Ans 1: " + ans1.content + " | Dung/Sai: " + ans1.isCorrect);
        System.out.println("Ans 2: " + ans2.content + " | Dung/Sai: " + ans2.isCorrect);
        System.out.println("Ans 3: " + ans3.content + " | Dung/Sai: " + ans3.isCorrect);

        System.out.println("\n--- THONG TIN EXAM ---");
        System.out.println("Exam 1 Title: " + exam1.title + " | Code: " + exam1.code);
        System.out.println("Exam 2 Title: " + exam2.title + " | Code: " + exam2.code);
        System.out.println("Exam 3 Title: " + exam3.title + " | Code: " + exam3.code);

        System.out.println("\n--- THONG TIN EXAM QUESTION ---");
        System.out.println("EQ 1 Exam: " + eq1.exam.title + " | Cau hoi: " + eq1.question.content);
        System.out.println("EQ 2 Exam: " + eq2.exam.title + " | Cau hoi: " + eq2.question.content);
        System.out.println("EQ 3 Exam: " + eq3.exam.title + " | Cau hoi: " + eq3.question.content);
    }
}