USE day15;

-- 创建表
CREATE TABLE teacher(
	id INT,
	NAME VARCHAR(20)
)
-- 查看所有表
SHOW TABLES;

DESC student;

DROP TABLE student;

CREATE TABLE student(
	id INT,
	NAME VARCHAR(20),
	gender VARCHAR(2),
	age INT
)

-- ********一、增删改数据********* ---
-- 1.1 增加数据
-- 插入所有字段。一定依次按顺序插入
INSERT INTO student VALUES(1,'张三','男',20);
-- 注意不能少或多字段值
-- INSERT INTO student VALUES(2,'李四','女');
-- 插入部分字段
INSERT INTO student(id,NAME) VALUES(2,'李四');

-- 1.2 修改数据
-- 修改所有数据（建议少用）
UPDATE student SET gender='女';
-- 带条件的修改（推荐使用）
UPDATE student SET gender='男' WHERE id=1; -- 修改id为1的学生，修改性别为男
-- 修改多个字段,注意: SET 字段名=值,字段名=值,....
UPDATE student SET gender='男',age=30 WHERE id=2;

-- 1.3 删除数据
-- 删除所有数据（建议少用）
DELETE FROM student;
-- 带条件的删除(推荐使用)
DELETE FROM student WHERE id=2;
--  另一种方式
-- delete from: 可以全表删除      1)可以带条件删除  2）只能删除表的数据，不能删除表的约束     3)使用delete from删除的数据可以回滚（事务）
-- truncate table: 可以全表删除   1）不能带条件删除 2）即可以删除表的数据，也可以删除表的约束 3）使用truncate table删除的数据不能回滚
TRUNCATE TABLE student;


CREATE TABLE test(
	id INT PRIMARY KEY AUTO_INCREMENT, -- 自增长约束
	NAME VARCHAR(20)
)
DESC test;

-- 1.
DELETE FROM test;
-- 2
TRUNCATE TABLE test;

INSERT INTO test(NAME) VALUES('张三');
INSERT INTO test(NAME) VALUES('张三2');
INSERT INTO test(NAME) VALUES('张三3');

SELECT * FROM test;

-- truncate table student where id=2; 不能带条件

-- 查询数据
SELECT * FROM student;

-- ********二、查询数据（select）*******--
-- 2.1 查询所有列
SELECT * FROM student;

-- 2.2 查询指定列
SELECT id,NAME,gender FROM student;

-- 2.3 查询时指定别名（as）
-- 注意： 在多表查询是经常使用表的别名
SELECT id AS '编号',NAME AS '姓名' FROM student;

-- 2.4 查询时添加常量列
-- 需求： 在查询student表时添加一个班级列，内容为“java就业班”
SELECT id,NAME,gender,age,'java就业班' AS '年级'  FROM student;

-- 2.5 查询时合并列
-- 需求： 查询每个学生的servlet和jsp的总成绩
SELECT id,NAME,(servlet+jsp) AS '总成绩' FROM student;
-- 注意：合并列只能合并数值类型的字段
SELECT id,(NAME+servlet) FROM student;

-- 2.6 查询时去除重复记录(DISTINCT)
-- 需求： 查询学生的性别     男 女
SELECT DISTINCT gender FROM student;
-- 另一种语法
SELECT DISTINCT(gender) FROM student;
-- 需求: 查询学生所在的地区
SELECT DISTINCT address FROM student;

-- 2.7 条件查询(where)
-- 2.7.1 逻辑条件： and(与)     or(或)
-- 需求： 查询id为2，且姓名为李四的学生
SELECT * FROM student WHERE id=2 AND NAME='李四'; -- 交集

-- 需求： 查询id为2，或姓名为张三的学生
SELECT * FROM student WHERE id=2 OR NAME='张三'; -- 并集

-- 2.7.2 比较条件： >   <   >=  <=  =  <>(不等于)     between and (等价于>= 且 <=)
-- 需求： 查询servlet成绩大于70分的学生
SELECT * FROM student WHERE servlet>70;

-- 需求： 查询jsp成绩大于等于75，且小于等于90分的学生
SELECT * FROM student WHERE jsp>=75 AND jsp<=90;
-- 另一个语法
SELECT * FROM student WHERE jsp BETWEEN 75 AND 90; -- (包前包后)

SELECT * FROM student WHERE gender<>'男';


-- 2.7.3 判空条件(null 空字符串)：  is null / is not null / =''  / <>''
-- 需求： 查询地址为空的学生（包括null和空字符串）
-- null vs  空字符串
-- null：表示没有值
-- 空字符串：有值的！
-- 判断null
SELECT * FROM student WHERE address IS NULL ;
-- 判断空字符串
SELECT * FROM student WHERE address='';

SELECT * FROM student WHERE address IS NULL OR address=''; -- （包括null和空字符串）

-- 需求： 查询有地址的学生(不包括null和空字符串)
SELECT * FROM student WHERE address IS NOT NULL AND address<>'';


-- 2.7.4 模糊条件： like
-- 通常使用以下替换标记：
-- % : 表示任意个字符
-- _ : 表示一个字符
-- 需求： 查询姓‘张’的学生
SELECT * FROM student WHERE NAME LIKE '李%';

-- 需求： 查询姓‘李’，且姓名只有两个字的学生
SELECT * FROM student WHERE NAME LIKE '李_';


--- 练习 --
CREATE TABLE student2(
	id INT,
	NAME VARCHAR(20),
	chinese FLOAT,
	english FLOAT,
	math FLOAT
);

INSERT INTO student2(id,NAME,chinese,english,math) VALUES(1,'张小明',89,78,90);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(2,'李进',67,53,95);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(3,'王五',87,78,77);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(4,'李一',88,98,92);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(5,'李来财',82,84,67);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(6,'张进宝',55,85,45);
INSERT INTO student2(id,NAME,chinese,english,math) VALUES(7,'黄蓉',75,65,30);



-- 查询表中所有学生的信息。
SELECT * FROM student2;

-- 查询表中所有学生的姓名和对应的英语成绩。
SELECT NAME,english FROM student2;

-- 过滤表中英语成绩的重复数据
SELECT DISTINCT(english) FROM student2;

-- 使用别名表示学生分数。
SELECT NAME AS '姓名',chinese AS '语文',english AS '英语',math AS '数学' FROM student2; 

-- 查询姓名为李一的学生成绩
SELECT * FROM student2 WHERE NAME='李一';

-- 查询英语成绩大于等于90分的同学
SELECT * FROM student2 WHERE english>=90;

-- 查询总分大于200分的所有同学
SELECT * FROM student2 WHERE (chinese+english+math)>200;
	
-- 查询所有姓李的学生英语成绩。
SELECT NAME,english FROM student2 WHERE NAME LIKE '李%';

-- 查询英语>80或者总分>200的同学
SELECT * FROM student2 WHERE english>80 OR (chinese+english+math)>200

-- 统计每个学生的总分。
SELECT id,NAME,(chinese+english+math) AS '总成绩' FROM student2;

-- 在所有学生总分数上加10分特长分。
SELECT id,NAME,(chinese+english+math+10) AS '总成绩' FROM student2;

SELECT * FROM student;


-- 2.8 聚合查询（使用聚合函数的查询）
 -- 常用的聚合函数： sum()  avg()  max()  min()  count()
-- 需求：查询学生的servlet的总成绩 (sum() :求和函数)
SELECT SUM(servlet) AS 'servlet的总成绩' FROM student;

-- 需求： 查询学生的servlet的平均分
SELECT AVG(servlet) AS 'servlet的平均分' FROM student;

-- 需求: 查询当前servlet最高分
SELECT MAX(servlet) AS '最高分' FROM student;

-- 需求： 查询最低分
SELECT MIN(servlet) AS '最低分' FROM student;

-- 需求： 统计当前有多少学生(count(字段))
SELECT COUNT(*) FROM student;

SELECT COUNT(id) FROM student;

-- 注意：count（）函数统计的数量不包含null的数据
-- 使用count统计表的记录数，要使用不包含null值的字段
SELECT COUNT(age) FROM student;


SELECT * FROM student;
-- 2.9 分页查询（limit 起始行,查询几行）
-- 起始行从0开始
-- 分页：当前页  每页显示多少条
-- 分页查询当前页的数据的sql: SELECT * FROM student LIMIT (当前页-1)*每页显示多少条,每页显示多少条;
--起始行=起始页-1；每页显示多少条记录自己定

-- 需求： 查询第1,2条记录（第1页的数据）
SELECT * FROM student LIMIT 0,2;
-- 查询第3,4条记录（第2页的数据）
SELECT * FROM student LIMIT 2,2;
-- 查询第5,6条记录（第3页的数据）
SELECT * FROM student LIMIT 4,2;
-- 查询第7,8条记录 (没有记录不显示)
SELECT * FROM student LIMIT 6,2;

-- 2.10 查询排序（order by ）
-- 语法 ：order by 字段 asc/desc
-- asc: 顺序，正序。数值：递增，字母：自然顺序（a-z）
-- desc: 倒序，反序。数值：递减，字母：自然反序(z-a)

-- 默认情况下，按照插入记录顺序排序
SELECT * FROM student;

-- 需求： 按照id顺序排序
SELECT * FROM student ORDER BY id ASC;
SELECT * FROM student ORDER BY id; -- 默认正序

SELECT * FROM student ORDER BY id DESC;-- 反序

-- 注意：多个排序条件
-- 需求： 按照servlet正序，按照jsp的倒序
SELECT * FROM student ORDER BY servlet ASC,jsp DESC;

-- 2.11 分组查询(group by)
-- 需求： 查询男女的人数
-- 预期结果：
  --  男   3
  --- 女   2
  -- 1) 把学生按照性别分组(GROUP BY gender)
  -- 2) 统计每组的人数(COUNT(*))
SELECT gender,COUNT(*) FROM student GROUP BY gender;

-- 2.12 分组查询后筛选
-- 需求： 查询总人数大于2的性别
-- 1) 查询男女的人数
select gender,count(*) from student group by gender;
-- 2）筛选出人数大于2的记录(having)

--- 注意： 分组之前条件使用where关键字，分组之后条件使用having关键字
SELECT gender,COUNT(*) FROM student GROUP BY gender HAVING COUNT(*)>2;

















-- 给student表添加servlet和jsp成绩列
ALTER TABLE student ADD servlet INT,ADD jsp INT;
ALTER TABLE student ADD servlet INT;
ALTER TABLE student ADD address VARCHAR(10);
DESC student;
UPDATE student SET servlet=70,jsp=85 WHERE id=1;
UPDATE student SET servlet=65,jsp=90 WHERE id=2;
UPDATE student SET gender='女' WHERE id=2;
UPDATE student SET address='广州天河' WHERE id=1;
UPDATE student SET address='广州天河' WHERE id=2;
UPDATE student SET address='广州番禺' WHERE id=3;

INSERT INTO student VALUES(4,'陈六','男',28,75,80,'');
INSERT INTO student VALUES(5,'李七','男',30,64,83,NULL);
INSERT INTO student VALUES(6,'李八八','男',35,67,82,'广州天河');








