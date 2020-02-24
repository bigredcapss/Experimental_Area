#### MySql遇到的问题总结：

###### 1.mysql 无法退出sql命令行编辑[遇到下面的情况无法终止sql编辑，最后没办法只能ctrl+c，强退了mysql]

```sql
'>
'>
">
类似这样的情况，不知道神马意思，怎么都结束不了；
谷歌可知：
mysql>：意思是准备好接受新命令。

->：意思是等待多行命令的下一行。

‘>：意思是等待下一行，等待以单引号(“’”)开始的字符串的结束。

“>：意思是等待下一行，等待以双引号(“””)开始的字符串的结束。

`>：意思是等待下一行，等待以反斜点(‘`’)开始的识别符的结束。

/*>：意思是等待下一行，等待以/*开始的注释的结束。


```

###### 2.命令行执行mysql插入数据Incorrect string value: '\xCD\xF5\xCE\xE5' for column...

```Sql
解决1：
查看数据库编码：show variables like 'character_set_database%';
更改数据库编码：alter database day23 default character set utf8;
查看数据表编码：show create table student;
更改数据表编码：alter table student character set utf8;
查看数据列编码：show full columns from student;
修改数据列编码：alter table student modify column name varchar(20) character set utf8mb4 collate utf8mb4_unicode_ci;

不行！！！！！！！

解决2：
mysql>set names gbk;
执行set names gbk;并不影响my.ini文件下之前设置的编码；
使用“set names gbk;”来指定客户端发送mysql语句时的编码格式，并且通过show variables like ‘char%'可以看到client、connection、results三个变量的编码格式变为了gbk；
注意该命令只在当前会话期间有效；

解决了!!!!!!!

```

###### 不牢固的sql语句与概念

```sql
--查询时添加常量列
select id,name,gender,age 'java1班' as '年级';
--添加d多列
alter table student add servlet int,add jsp int;
--查询时合并列（合并列只能合并数值类型的）
select id,name (servlet+jsp) as '总成绩' from student;
--分页查询
--limit 起始行，每页显示的行数
--起始行=当前页-1
select * from student limit 0,2;
--判空查询 is not null/is null/<>''
--'' vs NULL
--'':表示空字符串,有值
--NULL:没有值
select * from student where address is not null and address<>'';

--数据约束
①Default
②Primary key
③Not Null
④Unique:(Null可重复)
⑤Auto_increment
⑥Foreign key
⑦Check
⑦on delete cascade on update cascade

--三大范式
第一范式：要求数据表的每个字段必须是不可分割的独立单元；
第二范式：在第一范式的基础上，要求每张表只表达一个意思，表的每个字段都和表的主键有依赖；
第三范式：在第二范式的基础上，要求每张表的主键之外的其它字段都和主键有直接依赖关系（或者说有决定性的关系）

--多表查询
--规则：①确定查询的字段；②确定查询的表；③确定连接条件；
--注意：顺序一定不能乱；
左外连接查询：使用左边的表去匹配右边的表的数据，如果符合连接条件，则显示结果；如果不符合连接条件，则左边表的数据一定会显示；
 select deptName,empName
    -> from dept d
    -> left outer join employee e
    -> on d.id = e.deptId;
右外连接查询：使用右边的表去匹配左边的表的数据，如果古河连接条件，则显示结果；如果不符合连接条件，则右边表的数据一定会显示；
全连接查询：只要左右两边的表中有数据就返回；
自连接查询：自己虚拟出来一张表，在做树状结构的查询时，会用自连接；
SELECT e.empName,b.empName
	FROM employee e 
	LEFT OUTER JOIN employee b
	ON e.bossId=b.id;

SELECT * FROM employee;
SELECT * FROM dept;
-- 添加上司ID
ALTER TABLE employee ADD bossId INT;
UPDATE employee SET bossId=1 WHERE id=2;
UPDATE employee SET bossId=2 WHERE id=3;
UPDATE employee SET bossId=3 WHERE id=4;


--select into

--alter表时加约束
添加单列unique约束：alter table employee add unique(empName);
添加多列unique约束：alter table employee add constraint uq_employee unique (id,empName);
删除unique约束：alter table employee drop index uq_employee;

添加单列主键约束：alter table employee add primary key(id);
添加多列主键约束：alter table employee add constraint pk_employee primary key(id,empName);
删除主键约束：alter table employee drop primary key;

添加单列外键约束：alter table employee add foreign key (deptId) references dept(id);
添加多列外键约束：alter table employee add constraint fk_employee foreign key(deptId) reference dept(id);
删除外键约束：alter table employee drop foreign key fk_employee;

添加单列检查约束：alter table employee add check(id>0);
添加多列检查约束：alter table employee add constraint ck_employee check (id>0 and city='Snadnes');
删除检查约束：alter table employee drop check ck_employee;

添加单列默认约束：alter table employee alter city set default 'henan';
删除默认约束：alter table employee alter city drop default;

--MySql的变量

--全局变量（内置变量）：mysql数据库内置的变量（所有连接都起作用）
-- 查看所有全局变量：show variables；
-- 查看某个全局变量：select @@变量名；
-- 修改全局变量： set 变量名 = 新值；
--character_set_client:mysql服务器接收数据的编码；
--character_set_result:mysql服务器输出数据的编码；

--会话变量：只存在于当前客户端与数据库服务器端的一次连接中，如果连接断开，那么会话变量全部丢失；
--定义会话变量:set @变量 = 值；
--查看会话变量：select @变量；

--局部变量：在存储过程中使用的变量就叫局部变量，只要存储过程执行完毕，局部变量就会丢失；
--查看局部变量：select 变量；

--mysql备份与还原
--备份：mysql dump -uroot -proot day23 > d:/bak.sql;
--恢复：mysql -uroot -proot day23 < d:/bak.sql;恢复前要建一个空的同名的数据库；
注意：备份，恢复都不要登录数据库；

```

