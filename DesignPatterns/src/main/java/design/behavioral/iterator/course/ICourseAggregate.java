package design.behavioral.iterator.course;

/**
 * 抽象容器角色----自定义的课程集合接口
 */
public interface ICourseAggregate {
    void add (Course course);
    void remove (Course course);
    Iterator<Course> iterator ();
}
