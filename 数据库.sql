CREATE TABLE IF NOT EXISTS`class`(
`class_id` INTEGER NOT NULL  COMMENT '班级',
`classtime` DATETIME DEFAULT NULL COMMENT '入班时间',
PRIMARY KEY(`class_id`)   
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `students`(
`id` INTEGER NOT NULL AUTO_INCREMENT COMMENT '学号',
`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
`sex` VARCHAR(2) NOT NULL DEFAULT '女' COMMENT '性别',
`classid` INTEGER NOT NULL COMMENT '班级',
PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `students`(`name`,`sex`,`classid`) 
VALUES ('张三','男','1'),('小红','女','2'),('小白','男','4'),('小花','女','3')

INSERT INTO `class`(`class_id`,`classtime`)
VALUES ('1','2019-3-4'),('2','2019-3-4')

SELECT id,NAME,sex,classid,class_id,classtime
FROM students AS s
INNER JOIN class AS c
WHERE s.classid=c.class_id


INSERT  INTO students(`name`,`sex`,`classid`) VALUES ('小黑','男','2')

EXPLAIN SELECT COUNT(*) AS ROW
FROM students;

