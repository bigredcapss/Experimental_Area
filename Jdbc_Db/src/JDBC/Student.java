package JDBC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description:Student实体
 * @Author:BigRedCaps
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    //流水编号
    private int flowId;
    //考试类型
    private int type;
    //身份证号
    private String idCard;
    //准考证号
    private String examCard;
    //学生姓名
    private String studentName;
    //家庭住址
    private String location;
    //成绩
    private int grade;
}
