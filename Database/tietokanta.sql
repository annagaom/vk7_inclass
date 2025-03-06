create database vk7_inclass;
use vk7_inclass;
CREATE TABLE Student
(
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(100),
    rank VARCHAR(40),
    joinDate DATE
);

CREATE TABLE Instructor
(
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    specialization VARCHAR(100) ,
    experienceYear INT
);

CREATE TABLE TrainingSession
(
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    location VARCHAR(255) NOT NULL,
    duration INT,
    instructor_id INT NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES Instructor(id)
);

CREATE TABLE ProgressReport
(
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reportDate INT NOT NULL,
    achievements TEXT,
    areasForImprovement TEXT,
    student_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(id)
);

CREATE TABLE Attendacen
(
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status INT NOT NULL,
    notes TEXT,
    student_id INT NOT NULL,
    trainingSession_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (trainingSession_id) REFERENCES TrainingSession(id),
    UNIQUE (student_id, trainingSession_id)
);
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status INT NOT NULL,
    notes VARCHAR(300),
    student_id INT NOT NULL,
    trainingSession_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (trainingSession_id) REFERENCES TrainingSession(id),
    UNIQUE (student_id, trainingSession_id)
);



INSERT INTO Student (id, name, email, rank, joinDate)
VALUES
    (1, 'Nina', 'Ninja', 'red', '2025-02-04'),
    (2, 'Anna', 'anna@gail.com', 'red', '2014-06-09'),
    (3, 'Viivi', 'viivi@gail.com', 'white', '2025-01-28'),
    (4, 'Linda', 'linda@gail.com', 'green', '2024-07-26'),
    (5, 'Niki', 'niki@gail.com', 'red', '2025-03-05'),
    (7, 'Nini', 'nini@gmail.com', 'red', '2025-02-21');


INSERT INTO ProgressReport (reportDate, achievements, areasForImprovement, student_id)
VALUES
    ('2025-02-01', 'Improved problem-solving skills', 'Needs to work on time management', 1),
    ('2024-02-02', 'Completed a group project successfully', 'Should focus more on details', 2),
    ('2025-03-03', 'Showed great leadership skills', 'Needs to participate in discussions', 3),
    ('2024-03-04', 'Scored highest in math test', 'Should improve handwriting', 4),
    ('2025-03-09', 'Helped classmates with assignments', 'Needs to be more confident while speaking', 5);

INSERT INTO Instructor (name, specialization, experienceYear)
VALUES
    ('Jukka', 'Aikido - Basic Techniques', 10),
    ('Emma', 'Aikido - Weapons Training', 8),
    ('Paula', 'Aikido - Self-Defense Applications', 12),
    ('Visa', 'Aikido - Advanced Throws', 6),
    ('Hanna', 'Aikido - Basic Techniques', 15);

INSERT INTO TrainingSession (date, location, duration, instructor_id)
VALUES
    ('2024-03-10', 'Dojo Hall 1', '2 hours', 1),
    ('2024-03-12', 'Dojo Hall 2', '3 hours', 2),
    ('2024-03-15', 'Outdoor Training Area', '1.5 hours', 3),
    ('2024-03-18', 'Main Dojo', '2.5 hours', 4),
    ('2024-03-20', 'Zen Room', '2 hours', 5);

INSERT INTO Attendance (status, notes, student_id, trainingSession_id)
VALUES
    (1, 'Attended and demonstrated good ukemi skills', 1, 1),
    (0, 'Absent due to illness', 2, 1),
    (1, 'Joined late but performed well in weapons training', 1, 2),
    (1, 'Showed strong technique and fluid movement', 4, 3),
    (0, 'Missed due to personal reasons', 5, 4);


