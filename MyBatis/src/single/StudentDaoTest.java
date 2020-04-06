package single;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class StudentDaoTest
{
    StudentDao dao = new StudentDao();
    @Test
    public void add () throws Exception
    {
//        dao.add(new Student(1,"哈哈",7000D));
//        dao.add(new Student(2,"呵呵",8000D));
//        dao.add(new Student(3,"班长",9000D));
        dao.add(new Student(4,"哈士奇",10000D));
        dao.add(new Student(5,"哈哼",10000D));
        dao.add(new Student(6,"哒哒",6000D));
        dao.add(new Student(7,"奇奇",8000D));
        dao.add(new Student(8,"家奇",9000D));
        dao.add(new Student(9,"士奇",10000D));
        dao.add(new Student(10,"想想",10000D));
    }

    @Test
    public void findById () throws Exception
    {
        Student student = dao.findById(4);
        System.out.println(student.getName());
    }

    @Test
    public void findAll () throws Exception
    {
        List<Student> studentList = dao.findAll();
        for(Student student : studentList){
        	System.out.print(student.getId()+":"+student.getName()+":"+student.getSal());
        	System.out.println();
        }
    }

    @Test
    public void update () throws Exception
    {
        Student student = dao.findById(3);
        student.setName("靓班长");
        dao.update(student);
        System.out.println(student.getName());
    }

    @Test
    public void delete () throws Exception
    {
        Student student = dao.findById(4);
        System.out.print(student.getId()+":"+student.getName()+":"+student.getSal());
        dao.delete(student);
    }

    @Test
    public void findAllWithFy () throws Exception
    {
        System.out.println("--------------------第一页");
        List<Student> studentList1 = dao.findAllWithFy(0,3);
        for(Student s : studentList1){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
        System.out.println("--------------------第二页");
        List<Student> studentList2 = dao.findAllWithFy(3,3);
        for(Student s : studentList2){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
        System.out.println("--------------------第三页");
        List<Student> studentList3 = dao.findAllWithFy(6,3);
        for(Student s : studentList3){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
    }

    @Test
    public void findAllByNameWithFy () throws Exception
    {
        System.out.println("--------------------第一页");
        List<Student> studentList1 = dao.findAllByNameWithFy("哈",0,3);
        for(Student s : studentList1){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
        System.out.println("--------------------第二页");
        List<Student> studentList2 = dao.findAllByNameWithFy("哈",3,3);
        for(Student s : studentList2){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
        System.out.println("--------------------第三页");
        List<Student> studentList3 = dao.findAllByNameWithFy("哈",6,3);
        for(Student s : studentList3){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
    }

    @Test
    public void findAllByDynamicSql () throws Exception
    {
        List<Student> studentList = dao.findAllByDynamicSql(null,null,7000D);
        for(Student s : studentList){
            System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
        }
    }

    @Test
    public void dynaUpdate () throws Exception
    {
        //关注SQL的变化
        dao.dynaUpdate(1,null,9000D);//update students set sal=? where id=?
        dao.dynaUpdate(1,"笨笨",null);//update students set name=? where id=?
        dao.dynaUpdate(1,"笨笨",10000D);//update students set name=? and sal=? where id=?
    }

    @Test
    public void dynaDeleteArray () throws Exception
    {
        dao.dynaDeleteArray(new int[]{1,3,5,7,77});
        dao.dynaDeleteArray(1,3,5,7,77);
        dao.dynaDeleteArray(2,4,444);


    }

    @Test
    public void dynaDeleteList () throws Exception
    {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(6);
        ids.add(8);
        ids.add(9);
        dao.dynaDeleteList(ids);
    }

    @Test
    public void dynaInsert () throws Exception
    {
        dao.dynaInsert(new Student(1,"哈哈",7000D));//insert into 表名(*,*,*) values(?,?,?)
        dao.dynaInsert(new Student(2,"哈哈",null));//insert into 表名(*,*) values(?,?)
        dao.dynaInsert(new Student(3,null,7000D));//insert into 表名(*,*) values(?,?)
        dao.dynaInsert(new Student(4,null,null));//insert into 表名(*) values(?)
    }
}
