#1. Database
CREATE SCHEMA student_cms_plusplus DEFAULT CHARACTER SET utf8mb4;
USE student_cms_plusplus;
#2. Tables
#2.1: Table student
CREATE TABLE student(
	id int primary key auto_increment,
    name varchar(30) not null,
    mssv char(8) not null,
    password varchar(30) not null,
    phone varchar(10),
    address varchar(50),
    age smallint,
    email varchar(30),
    created_timestamp timestamp default current_timestamp,
    last_updated_timestamp timestamp default current_timestamp
);
#2.2: Table class
CREATE TABLE class(
	id int primary key auto_increment,
    name varchar(50) not null,
    major varchar(50),
    total_student smallint not null,
    teacher_name varchar(30) not null,
    teacher_phone varchar(10),
    created_timestamp timestamp default current_timestamp,
    last_updated_timestamp timestamp default current_timestamp
);
#2.3: Table student_class
CREATE TABLE student_class(
	student_id int,
    class_id int,
    primary key (student_id, class_id),
    foreign key (student_id) references student(id),
    foreign key (class_id) references class(id)
);

#3. Using workbench add sample data for 3 tables. Copy REVIEW sql insert.
#Table student
insert into student (name, mssv, password, phone, address, age, email) values ('Hugues Deal', 20184340, '5l5Q8Z', null, null, 19, 'hdeal0@stanford.edu');
insert into student (name, mssv, password, phone, address, age, email) values ('Wallis Pooly', 20180214, 'e5U088jj95i', null, null, 19, 'wpooly1@state.tx.us');
insert into student (name, mssv, password, phone, address, age, email) values ('Zacharias Pinare', 20176800, 'tsDeMZ', '6008887224', '68909 Warbler Way', 20, 'zpinare2@netvibes.com');
insert into student (name, mssv, password, phone, address, age, email) values ('Frazier Kybird', 20160390, 'zGLVHLt', '4021337221', '91 Roxbury Place', 18, 'fkybird3@google.nl');
insert into student (name, mssv, password, phone, address, age, email) values ('Cullie Tissington', 20150127, 'FhYjs2', null, null, 21, 'ctissington4@about.com');
insert into student (name, mssv, password, phone, address, age, email) values ('Vinni Jodrellec', 20180344, 'DTaSU3PLpd7', '3335313520', '7 Walton Road', 18, 'vjodrellec5@multiply.com');
insert into student (name, mssv, password, phone, address, age, email) values ('Ailina Beall', 20177370, 'wgc9Ld', null, null, null, null);
insert into student (name, mssv, password, phone, address, age, email) values ('Yoshi Pendred', 20153027, 'vJf6qs', '7993554629', '0 4th Trail', 18, 'ypendred7@ucsd.edu');
insert into student (name, mssv, password, phone, address, age, email) values ('Karine Rodie', 20197457, 'f84ZkO', null, null, 21, 'krodie8@ihg.com');
insert into student (name, mssv, password, phone, address, age, email) values ('Parnell Fresson', 20193900, 'upyLEzc', null, null, 21, 'pfresson9@ask.com');
#Table class
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Accounting', 'Accounting', 24, 'Monica Mease', null);
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Animal Biology', 'Animal Biology', 38, 'Erda Izon', '1981172892');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Animal Science', 'Animal Science', 6, 'Guillaume Beldham', '3668407153');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Biochemistry', 'Biochemistry', 40, 'Lombard Harral', '8181077134');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Economics', 'Economics', 43, 'Lilia Norris', '2623252815');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Electrical Engineering', 'Electrical Engineering', 4, 'Eddy Galbraeth', '7471301210');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Physics', 'Physics', 16, 'Lucilia Searchwell', null);
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Maths', 'Mathematical Science', 40, 'Vernen MacCrosson', '5275394549');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Marketing Management', 'Marketing Management', 49, 'Edvard Elphee', '5892637329');
insert into class (name, major, total_student, teacher_name, teacher_phone) values ('Intro to Food Science', 'Food Science', 38, 'Christie Lidierth', '2691551138');
#Table student_class

insert into student_class (student_id, class_id) values (2, 8);
insert into student_class (student_id, class_id) values (1, 4);
insert into student_class (student_id, class_id) values (4, 3);
insert into student_class (student_id, class_id) values (5, 3);
insert into student_class (student_id, class_id) values (10, 4);
insert into student_class (student_id, class_id) values (6, 4);
insert into student_class (student_id, class_id) values (6, 3);
insert into student_class (student_id, class_id) values (7, 7);
insert into student_class (student_id, class_id) values (8, 8);
insert into student_class (student_id, class_id) values (6, 5);



