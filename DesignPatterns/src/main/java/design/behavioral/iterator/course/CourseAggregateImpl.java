package design.behavioral.iterator.course;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体容器
 */
public class CourseAggregateImpl implements ICourseAggregate {
    private List courseList;

    public CourseAggregateImpl() {
        this.courseList = new ArrayList();
    }

    public void add(Course course) {
        courseList.add(course);
    }

    public void remove(Course course) {
        courseList.remove(course);
    }

    public Iterator<Course> iterator() {
        return new IteratorImpl<Course>(courseList);
    }
}
