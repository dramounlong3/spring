CREATE TABLE IF NOT EXISTS threetier
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(30)  ,
    courseList  VARCHAR(200) ,
    todoList    VARCHAR(200) ,
    score       DOUBLE       ,
    graduate    BOOLEAN      ,
    create_date TIMESTAMP
);

