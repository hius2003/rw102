-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó 
DELIMITER $$
CREATE PROCEDURE get_account_by_department(IN p_department_name VARCHAR(100))

BEGIN
    SELECT a.*
    FROM account a
    JOIN department d
	ON a.department_id = d.department_id
    WHERE d.department_name = p_department_name;
END $$

DELIMITER ;
CALL get_account_by_department('Test');

-- Question 2: Tạo store để in ra số lượng account trong mỗi group 
DELIMITER $$
CREATE PROCEDURE count_account_in_group()
BEGIN
    SELECT g.group_id,
           g.group_name,
           COUNT(ga.account_id) AS total_account
    FROM group_table g
    LEFT JOIN group_account ga
        ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name;
END $$

DELIMITER ;

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại
DELIMITER $$
CREATE PROCEDURE count_question_by_type_current_month()
BEGIN
    SELECT tq.type_id,
           tq.type_name,
           COUNT(q.question_id) AS total_question
    FROM type_question tq
    LEFT JOIN question q
        ON tq.type_id = q.type_id
       AND MONTH(q.created_date) = MONTH(CURRENT_DATE())
       AND YEAR(q.created_date) = YEAR(CURRENT_DATE())
    GROUP BY tq.type_id, tq.type_name;
END $$

DELIMITER ;

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất
DELIMITER $$
CREATE PROCEDURE get_type_question_most_questions()
BEGIN
	SELECT type_id
	FROM question
	GROUP BY type_id
	HAVING COUNT(question_id) = (
		SELECT MAX(total_question)
		FROM (
			SELECT COUNT(question_id) AS total_question
			FROM question
			GROUP BY type_id
			) AS temp
	);
END $$
DELIMITER ;
-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question 
-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào image
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
DELIMITER $$

CREATE PROCEDURE create_account(
    IN p_full_name VARCHAR(100),
    IN p_email VARCHAR(100)
)
BEGIN
    DECLARE v_username VARCHAR(100);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;

    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);

    SELECT position_id
    INTO v_position_id
    FROM position
    WHERE position_name = 'Developer';

    SELECT department_id
    INTO v_department_id
    FROM department
    WHERE department_name = 'Waiting';

    INSERT INTO account
    (
        email,
        username,
        full_name,
        department_id,
        position_id
    )
    VALUES
    (
        p_email,
        v_username,
        p_full_name,
        v_department_id,
        v_position_id
    );

    SELECT *
    FROM account
    WHERE account_id = LAST_INSERT_ID();
END $$

DELIMITER ;

CALL create_account('Nguyen Van An','nguyenvanan@gmail.com');
-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất 
DELIMITER $$
CREATE PROCEDURE get_longest_question_by_type(
    IN p_type_name VARCHAR(100)
)
BEGIN
    SELECT q.question_id,
           q.content,
           tq.type_name,
           LENGTH(q.content) AS content_length
    FROM question q
    JOIN type_question tq
        ON q.type_id = tq.type_id
    WHERE tq.type_name = p_type_name
    ORDER BY LENGTH(q.content) DESC
    LIMIT 1;
END $$

DELIMITER ;
-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
DELIMITER $$
CREATE PROCEDURE delete_exam(
    IN p_exam_id INT
)
BEGIN
    DELETE FROM exam
    WHERE exam_id = p_exam_id;

    SELECT ROW_COUNT() AS deleted_exam;
END $$

DELIMITER ;
CALL delete_exam(1);


-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa)

-- ANH CHỮA CHO EM CÂU NÀY VỚI Ạ ------------------------------------------------


-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc 
--
-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DELIMITER $$

CREATE PROCEDURE count_question_each_month_this_year()
BEGIN
    SELECT MONTH(created_date) AS month,
           COUNT(*) AS total_question
    FROM question
    WHERE YEAR(created_date) = YEAR(CURRENT_DATE())
    GROUP BY MONTH(created_date)
    ORDER BY MONTH(created_date);
END $$

DELIMITER ;


-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất 
-- 	ANH CHỮA CÂU NÀY ĐI ANH ƠI ----------------------------
