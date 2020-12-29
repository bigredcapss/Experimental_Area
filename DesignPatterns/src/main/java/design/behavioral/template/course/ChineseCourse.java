package design.behavioral.template.course;

/**
 * 语文学科--检查作业
 * @author BigRedCaps
 * @date 2020/12/27 14:32
 */
public class ChineseCourse extends AbstractCourse
{
    private  boolean needCheckHomework = false;

    public void setNeedCheckHomework(boolean needCheckHomework) {
        this.needCheckHomework = needCheckHomework;
    }

    @Override
    protected boolean needCheckHomework() {
        return this.needCheckHomework;
    }

    protected void checkHomework() {
        System.out.println("检查Java作业");
    }
}
