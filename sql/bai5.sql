-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
CREATE OR REPLACE VIEW v_employee_sale AS 
SELECT a.* FROM `account` a JOIN department d 
ON a.department_id = d.department_id 
WHERE d.department_name = "Sales";

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
CREATE OR REPLACE VIEW v_account_most_group AS 
SELECT a.* FROM `account` a JOIN group_account ga
ON a.account_id = ga.account_id 
GROUP BY a.account_id, a.username, a.full_name, a.email 
HAVING COUNT(ga.group_id) = (
	SELECT MAX(total_group)
    FROM (SELECT COUNT(ga.group_id) AS total_group
			FROM group_account
			GROUP BY ga.account_id
		 ) AS result
);


-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
CREATE OR REPLACE VIEW view_question_over_300_words AS
SELECT q.*
FROM question q
WHERE CHAR_LENGTH(q.content) > 300;

DELETE FROM question
WHERE question_id IN (
    SELECT question_id
    FROM v_question_over_300
);

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
CREATE OR REPLACE VIEW view_department_most_employee AS
SELECT d.department_id, d.department_name, COUNT(a.account_id) AS total_employee
FROM department d
JOIN `account` a 
ON d.department_id = a.department_id
GROUP BY d.department_id, d.department_name

HAVING COUNT(a.account_id) = (
SELECT MAX(total_employee)
FROM (
        SELECT COUNT(account_id) AS total_employee
        FROM account
        GROUP BY department_id
    ) AS temp
);

CREATE OR REPLACE VIEW v_question_created_by_nguyen AS
SELECT q.*, a.*
FROM question q
JOIN `account` a
ON q.creator_id = a.account_id
WHERE a.full_name LIKE 'Nguyen %';