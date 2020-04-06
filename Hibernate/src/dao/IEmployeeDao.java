package dao;

import bean.manytoone.Employee;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:数据Dao接口
 * @Author:BigRedCaps
 */
public interface IEmployeeDao
{
    /**
     * 保存对象
     * @param emp
     */
    void save(Employee emp);

    /**
     * 更新对象
     * @param emp
     */
    void update(Employee emp);

    /**
     * 主键查询
     * @param id
     * @return
     */
    Employee findById(Serializable id);

    /**
     * 获取对象集合
     * @return
     */
    List<Employee> getAll();

    /**
     * 按姓名查询
     * @param employeeName
     * @return
     */
    List<Employee> getAll(String employeeName);

    /**
     * 分页查询
     * @param index
     * @param count
     * @return
     */
    List<Employee> getAll(int index, int count);

    /**
     * 删除对象
     * @param id
     */
    void delete(Serializable id);
}
