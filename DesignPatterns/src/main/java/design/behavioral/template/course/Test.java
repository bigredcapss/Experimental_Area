package design.behavioral.template.course;

/**
 * 模板方法模式测试
 * @author BigRedCaps
 * @date 2020/12/27 14:35
 */
public class Test
{
    public static void main (String[] args)
    {
        System.out.println("=========小学课程=========");
        ChineseCourse chineseCourse = new ChineseCourse();
        chineseCourse.setNeedCheckHomework(false);
        chineseCourse.createCourse();


        System.out.println("=========初中课程=========");
        EnglishCourse englishCourse = new EnglishCourse();
        englishCourse.createCourse();
    }
}
