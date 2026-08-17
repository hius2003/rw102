DROP DATABASE IF EXISTS Testing_System_Assignment_2;
CREATE DATABASE Testing_System_Assignment_2;
USE Testing_System_Assignment_2;

CREATE TABLE Department
(
	DepartmentID 		TINYINT AUTO_INCREMENT PRIMARY KEY,
    DepartmentName 		VARCHAR(50) NOT NULL UNIQUE KEY
);

CREATE TABLE Position1
(
	PositionID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    PositionName 		ENUM('Dev','Test','Scrum Master','PM') NOT NULL UNIQUE KEY
);

CREATE TABLE Account1
(
	AccountID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    Email	 			VARCHAR(50) NOT NULL UNIQUE KEY,
    UserName			VARCHAR(50) NOT NULL UNIQUE KEY,
    FullName			VARCHAR(50) NOT NULL,
    DepartmentID		TINYINT NOT NULL,
    PositionID			TINYINT NOT NULL,
    CreateDate			DATE,
    
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID),
    FOREIGN KEY (PositionID) REFERENCES Position1(PositionID)
    
);

CREATE TABLE Group1
(
	GroupID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    GroupName			VARCHAR(50) NOT NULL UNIQUE KEY,
    CreatorID			TINYINT NOT NULL,
    CreateDate			DATE,
    
	FOREIGN KEY (CreatorID) REFERENCES Account1(AccountID)
    
);

CREATE TABLE Groupaccount
(
	GroupID 			TINYINT NOT NULL,
    AccountID 			TINYINT NOT NULL,
    JoinDate			DATE,
    PRIMARY KEY (GroupID, AccountID),
    
    FOREIGN KEY (GroupID) REFERENCES Group1(GroupID),
    FOREIGN KEY (AccountID) REFERENCES Account1(AccountID)
);

CREATE TABLE Typequestion
(
	TypeID 				TINYINT AUTO_INCREMENT PRIMARY KEY,
    TypeName			ENUM('Essay','Multiple-Choice')
);

CREATE TABLE Categoryquestion
(
	CategoryID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    CategoryName		VARCHAR(50) NOT NULL UNIQUE KEY
);

CREATE TABLE Question
(
	QuestionID			TINYINT AUTO_INCREMENT PRIMARY KEY,
    Content				VARCHAR(50) NOT NULL,
    CategoryID			TINYINT NOT NULL,
    TypeID				TINYINT NOT NULL,
    CreatorID			TINYINT NOT NULL,
    CreateDate			DATE,
    
    FOREIGN KEY (TypeID) REFERENCES Typequestion(TypeID),
    FOREIGN KEY (CategoryID) REFERENCES Categoryquestion(CategoryID),
    FOREIGN KEY (CreatorID) REFERENCES Account1(AccountID)
);

CREATE TABLE Answer
(
	AnswerID			TINYINT AUTO_INCREMENT PRIMARY KEY, 
    Content				VARCHAR(50) NOT NULL,
    QuestionID			TINYINT NOT NULL,
    isCorrect			BOOLEAN NOT NULL,
    
    FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

CREATE TABLE Exam
(
	ExamID				TINYINT AUTO_INCREMENT PRIMARY KEY,
    `Code`				VARCHAR(10) NOT NULL,
    Title				VARCHAR(50) NOT NULL,
    CategoryID			TINYINT NOT NULL,
    Duration			TINYINT NOT NULL,
    CreatorID			TINYINT NOT NULL,
    CreateDate			DATE,
    
    FOREIGN KEY (CategoryID) REFERENCES Categoryquestion(CategoryID),
	FOREIGN KEY (CreatorID) REFERENCES Account1(AccountID)
);

CREATE TABLE Examquestion
(
	ExamID 				TINYINT NOT NULL,
    QuestionID			TINYINT NOT NULL,
    PRIMARY KEY (ExamID, QuestionID),
    
    FOREIGN KEY (ExamID) REFERENCES Exam(ExamID),
    FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

-- question 1 -----------------------------------------------------------------
INSERT INTO Department(DepartmentName)
VALUES				
					(N'Marketing'	 ),
					(N'Sale'		 ),
					(N'Bảo vệ'		 ),
					(N'Nhân sự'		 ),
					(N'Ký thuật'	 ),
					(N'Tài chính'	 ),
					(N'Phó giám đốc' ),
					(N'Thư kí'		 ),
					(N'Bán hàng'	 ),
					(N'Giám đốc'	 );
                                
                                
INSERT INTO Position1(PositionName)
VALUES 				
					(N'Dev'			), 
					(N'Test'		), 
					(N'Scrum Master'), 
					(N'PM'			);


INSERT INTO Account1(Email, Username, FullName, DepartmentID, PositionID, CreateDate) 
VALUES
					(N'a1@gmail.com',  N'trun1',   N'ma trun 12', 1,  1, '2024-01-01' ),
					(N'a2@gmail.com',  N'trun2',   N'ma trun 1321', 2,  2, '2024-01-02' ),
					(N'a3@gmail.com',  N'trun3',   N'ma trun 3234', 3,  3, '2024-01-03' ),
					(N'a4@gmail.com',  N'trun4',   N'ma trun 42345', 4,  4, '2024-01-04' ),
					(N'a5@gmail.com',  N'trun5',   N'ma trun 535', 5,  1, '2024-01-05' ),
					(N'a6@gmail.com',  N'trun6',   N'ma trun 6352', 6,  2, '2024-01-06' ),
					(N'a7@gmail.com',  N'trun7',   N'ma trun 72', 7,  3, '2024-01-07' ),
					(N'a8@gmail.com',  N'trun8',   N'ma trun 8523', 8,  4, '2024-01-08' ),
					(N'a9@gmail.com',  N'trun9',   N'ma trun 93443', 9,  2, '2024-01-09' ),
					(N'a10@gmail.com', N'trun10',  N'ma trun 101', 10, 4, '2026-01-10');


INSERT INTO Group1(GroupName, CreatorID, CreateDate) 
VALUES
					(N'Group b1',  1,  N'2026-01-01'),
                    (N'Group b2',  2,  N'2026-01-01'),
                    (N'Group b3',  3,  N'2026-01-01'),
                    (N'Group b4',  4,  N'2026-01-01'),
                    (N'Group b5',  5,  N'2026-01-01'),
                    (N'Group b6',  6,  N'2026-01-01'),
                    (N'Group b7',  7,  N'2026-01-01'),
                    (N'Group b8',  8,  N'2026-01-01'),
                    (N'Group b9',  9,  N'2026-01-01'),
                    (N'Group b10', 10, N'2026-01-01');


INSERT INTO Groupaccount(GroupID, AccountID, JoinDate)
VALUES
					(1, 1, '2026-01-02'  ),
					(2, 2, '2026-01-03'  ),
					(3, 3, '2026-01-04'  ),
					(4, 4, '2026-01-05'  ),
					(5, 5, '2026-01-06'  ),
					(6, 6, '2026-01-07'  ),
					(7, 7, '2026-01-08'  ),
					(8, 8, '2026-01-09'  ),
					(9, 9, '2026-01-10'	 ),
					(10, 10, '2026-01-11');
                    
                    
INSERT INTO Typequestion(TypeName)
VALUES
					('Essay'          ),
					('Multiple-Choice');
                    
                    
INSERT INTO Categoryquestion(CategoryName)
VALUES
					(N'Java'       ),
					(N'SQL'        ),
					(N'HTML'       ),
					(N'CSS'        ),
					(N'JavaScript' ),
					(N'Python'     ),
					(N'Database'   ),
					(N'Testing'    ),
					(N'Spring Boot'),
					(N'Git'        );
                    
                    
INSERT INTO Question (Content, CategoryID, TypeID, CreatorID, CreateDate)
VALUES
					(N'Java la ngon ngu gi?', 1, 1, 1, '2026-02-01'),
					(N'SQL dung de lam gi?', 2, 2, 2, '2026-02-02'),
					(N'HTML dung de lam gi?', 3, 1, 3, '2026-02-03'),
					(N'CSS dung de lam gi?', 4, 2, 4, '2026-02-04'),
					(N'JavaScript dung de lam gi?', 5, 1, 5, '2026-02-05'),
					(N'Python la ngon ngu gi?', 6, 2, 6, '2026-02-06'),
					(N'Database la gi?', 7, 1, 7, '2026-02-07'),
					(N'Testing la gi?', 8, 2, 8, '2026-02-08'),
					(N'Spring Boot la gi?', 9, 1, 9, '2026-02-09'),
					(N'Git dung de lam gi?', 10, 2, 10, '2026-02-10');


INSERT INTO Answer(Content, QuestionID, isCorrect)
VALUES
					(N'Java la ngon ngu lap trinh', 1, TRUE),
					(N'SQL dung de truy van CSDL', 2, TRUE),
					(N'HTML dung de tao cau truc trang web', 3, TRUE),
					(N'CSS dung de dinh dang trang web', 4, TRUE),
					(N'JavaScript dung de lap trinh web', 5, TRUE),
					(N'Python la ngon ngu lap trinh', 6, TRUE),
					(N'Database la noi luu tru du lieu', 7, TRUE),
					(N'Testing la kiem thu phan mem', 8, TRUE),
					(N'Spring Boot la framework Java', 9, TRUE),
					(N'Git dung de quan ly ma nguon', 10, TRUE);


INSERT INTO Exam(`Code`, Title, CategoryID, Duration, CreatorID, CreateDate)
VALUES
					(N'EX001', N'Java Basic', 1, 60, 1, '2026-03-01'),
					(N'EX002', N'SQL Basic', 2, 60, 2, '2026-03-02'),
					(N'EX003', N'HTML Basic', 3, 45, 3, '2026-03-03'),
					(N'EX004', N'CSS Basic', 4, 45, 4, '2026-03-04'),
					(N'EX005', N'JavaScript Basic', 5, 60, 5, '2026-03-05'),
					(N'EX006', N'Python Basic', 6, 60, 6, '2026-03-06'),
					(N'EX007', N'Database Basic', 7, 60, 7, '2026-03-07'),
					(N'EX008', N'Testing Basic', 8, 45, 8, '2026-03-08'),
					(N'EX009', N'Spring Boot Basic', 9, 60, 9, '2026-03-09'),
					(N'EX010', N'Git Basic', 10, 45, 10, '2026-03-10');
     
     
INSERT INTO Examquestion(ExamID, QuestionID)
VALUES
					(1, 1),
					(2, 2),
					(3, 3),
					(4, 4),
					(5, 5),
					(6, 6),
					(7, 7),
					(8, 8),
					(9, 9),
					(10, 10);
-- ---------------------------------------------------------------------------------

-- question 2 ---------------------------------------------------------------------
SELECT * FROM Department;

-- question 3 --------------------------------------------------------------------
SELECT DepartmentID FROM department WHERE DepartmentName = 'Sale';

-- question 4 ---------------------------------------------------------------------


                    