DROP DATABASE IF EXISTS Testing_System_Assignment_1;
CREATE DATABASE Testing_System_Assignment_1;
USE Testing_System_Assignment_1;

CREATE TABLE department
(
	Department_ID 	TINYINT AUTO_INCREMENT PRIMARY KEY,
    Department_Name VARCHAR(30)
);

CREATE TABLE position
(
	positionID INT AUTO_INCREMENT PRIMARY KEY,
    positionName VARCHAR(50)
);

CREATE TABLE accounts
(
	accountID 		INT AUTO_INCREMENT PRIMARY KEY,
    email	 		VARCHAR(50),
    userName		VARCHAR(50),
    fullName		VARCHAR(50),
    departmentID	INT,
    positionID		INT,
    createDate		DATE
);

CREATE TABLE groups1
(
	groupID 		INT AUTO_INCREMENT PRIMARY KEY,
    groupName		VARCHAR(50),
    creatorID		INT,
    creatorDate		DATE 
);

CREATE TABLE groupaccount
(
	groupID 		INT,
    accountID 		INT,
    joinDate		DATE 
);

CREATE TABLE typequestion
(
	typeID 			INT AUTO_INCREMENT PRIMARY KEY,
    typeName		VARCHAR(50)
);

CREATE TABLE categoryquestion
(
	categoryID 		INT AUTO_INCREMENT PRIMARY KEY,
    categoryName	VARCHAR(50)
);

CREATE TABLE question
(
	questionID		INT AUTO_INCREMENT PRIMARY KEY,
    content			VARCHAR(50),
    categoryID		INT,
    typeID			INT,
    createID		INT,
    creatoDate		DATE 
);

CREATE TABLE answer
(
	answerID		INT AUTO_INCREMENT PRIMARY KEY, 
    content			VARCHAR(50),
    questionID		INT,
    isCorrect		BOOLEAN
);

CREATE TABLE exam
(
	examID			INT AUTO_INCREMENT PRIMARY KEY,
    code1			TINYINT,
    title			VARCHAR(50),
    categoryID		INT,
    duration		TIME,
    creatorID		INT,
    creatorDate		DATE 
);

CREATE TABLE examquestion
(
	examID 			INT AUTO_INCREMENT PRIMARY KEY,
    questionID		INT 
);
