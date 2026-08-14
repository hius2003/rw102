DROP DATABASE IF EXISTS Testing_System_Assignment_1;
CREATE DATABASE Testing_System_Assignment_1;
USE Testing_System_Assignment_1;

CREATE TABLE Department
(
	DepartmentID 		TINYINT AUTO_INCREMENT PRIMARY KEY,
    DepartmentName 		VARCHAR(50) NOT NULL UNIQUE KEY
);

CREATE TABLE `Position`
(
	PositionID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    PositionName 		ENUM('Dev','Test','Scrum Master','PM') NOT NULL UNIQUE KEY
);

CREATE TABLE `Account`
(
	AccountID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    Email	 			VARCHAR(50) NOT NULL UNIQUE KEY,
    UserName			VARCHAR(50) NOT NULL UNIQUE KEY,
    FullName			VARCHAR(50) NOT NULL,
    DepartmentID		TINYINT NOT NULL,
    PositionID			TINYINT NOT NULL,
    CreateDate			DATE,
    
    FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID),
    FOREIGN KEY (PositionID) REFERENCES `Position`(PositionID)
    
);

CREATE TABLE `Group`
(
	GroupID 			TINYINT AUTO_INCREMENT PRIMARY KEY,
    GroupName			VARCHAR(50) NOT NULL UNIQUE KEY,
    CreatorID			TINYINT NOT NULL,
    CreateDate			DATE,
    
	FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID)
    
);

CREATE TABLE Groupaccount
(
	GroupID 			TINYINT NOT NULL,
    AccountID 			TINYINT NOT NULL,
    JoinDate			DATE,
    PRIMARY KEY (GroupID, AccountID),
    
    FOREIGN KEY (GroupID) REFERENCES `Group`(GroupID),
    FOREIGN KEY (AccountID) REFERENCES `Account`(AccountID)
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
    FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID)
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
	FOREIGN KEY (CreatorID) REFERENCES `Account`(AccountID)
);

CREATE TABLE Examquestion
(
	ExamID 				TINYINT NOT NULL,
    QuestionID			TINYINT NOT NULL,
    PRIMARY KEY (ExamID, QuestionID),
    
    FOREIGN KEY (ExamID) REFERENCES Exam(ExamID),
    FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

