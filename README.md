# todo

To Start Application

1. mvn clean install
2. mvn spring-boot:run

Postman Collection

https://www.getpostman.com/collections/2f2b7c7c1080378e76d3

Database:

CREATE DATABASE todo;


CREATE TABLE tb_user(
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100),
	email VARCHAR(100) unique
);

CREATE TABLE tb_todo(
	todo_id INT auto_increment PRIMARY KEY,
	user_id_fk INT NOT NULL,
    title VARCHAR(200) NOT NULL,
    is_deleted boolean default false,
	FOREIGN KEY(user_id_fk) REFERENCES todo.tb_user (user_id),
    UNIQUE KEY(user_id_fk, title)
);

CREATE TABLE tb_task(
	task_id INT NOT NULL PRIMARY KEY auto_increment,
    text VARCHAR(1000) NOT NULL,
	is_deleted boolean default false,
    is_complete boolean default false
);

CREATE TABLE tb_todo_task(
	todo_id INT NOT NULL,
    task_id INT NOT NULL,
    foreign key(todo_id) references tb_todo(todo_id),
	foreign key(task_id) references tb_task(task_id),
    unique(todo_id, task_id)
);
