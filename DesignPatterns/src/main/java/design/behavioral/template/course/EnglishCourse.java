package design.behavioral.template.course;

/**
 * 英语学科--检查作业
 * @author BigRedCaps
 * @date 2020/12/27 14:34
 */
public class EnglishCourse extends AbstractCourse
{
    @Override
    protected void checkHomework ()
    {
        System.out.println("检查英语作业");
    }
}
