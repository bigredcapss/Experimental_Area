package single;

import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:持久化类
 * @Author:BigRedCaps
 */
public class StudentDao
{
    /**
     * 增加学生
     */
    public void add(Student student) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.insert(Student.class.getName()+".add",student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 根据ID查询学生 
     */
    public Student findById(int id) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            Student student = sqlSession.selectOne(Student.class.getName()+".findById",id);
            sqlSession.commit();
            return student;
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 查询所有学生 
     */
    public List<Student> findAll() throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            return sqlSession.selectList(Student.class.getName()+".findAll");
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 更新学生 
     */
    public void update(Student student) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.update(Student.class.getName()+".update",student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 删除学生 
     */
    public void delete(Student student) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.delete(Student.class.getName()+".delete",student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }

    /**
     * 无条件分页
     * @param start 表示在mysql中从第几条记录的索引号开始显示，索引从0开始
     * @param size 表示在mysql中最多显示几条记录
     */
    public List<Student> findAllWithFy(int start,int size) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();

            Map<String,Object> map = new LinkedHashMap<>();
            map.put("pstart",start);
            map.put("psize",size);
            return sqlSession.selectList(Student.class.getName()+".findAllWithFy",map);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 有条件分页
     */
    public List<Student> findAllByNameWithFy(String name,int start,int size) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("pname","%"+name+"%");
            map.put("pstart",start);
            map.put("psize",size);
            return sqlSession.selectList(Student.class.getName()+".findAllByNameWithFy",map);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }

    /**
     * 动态sql查询
     */
    public List<Student> findAllByDynamicSql(Integer id,String name,Double sal) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();

            Map<String,Object> map = new LinkedHashMap<String,Object>();
            map.put("pid",id);
            map.put("pname",name);
            map.put("psal",sal);

            return sqlSession.selectList("single.Student.findAllByDynamicSql",map);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }

    /**
     * 动态sql更新学生
     */
    public void dynaUpdate(Integer id,String name,Double sal) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();

            Map<String,Object> map = new HashMap<>();
            map.put("pid",id);
            map.put("pname",name);
            map.put("psal",sal);
            sqlSession.update("single.Student.dynaUpdate",map);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }

    /**
     * 根据ID批量删除学生(数组版本)
     */
    public void dynaDeleteArray(int... ids) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.delete("single.Student.dynaDeleteArray",ids);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
    /**
     * 根据ID批量删除学生(集合版本)
     */
    public void dynaDeleteList(List<Integer> ids) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.delete("single.Student.dynaDeleteList",ids);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }

    /**
     * 动态插入学生
     */
    public void dynaInsert(Student student) throws Exception{
        SqlSession sqlSession = null;
        try{
            sqlSession = MyBatisUtils.getSqlSession();
            sqlSession.insert("single.Student.dynaInsert",student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MyBatisUtils.closeSqlSession();
        }
    }
}
