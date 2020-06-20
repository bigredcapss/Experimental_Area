package design.creationmode.factory.abstractfactory;

/**
 * 抽象工厂测试
 *
 * 抽象工厂模式：随着工厂间竞争的加剧，不得不推成出新，于是进一步完善课程内容，引入课程笔记，录播视频等内容，提升课程质量
 * ，为规范工厂管理，设立课程总厂，Java,Python子工厂直接对总厂负责，同时设立课程笔记，录播视频等部门，进一步规范子工厂的
 * 管理；
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {

        JavaCourseFactory factory = new JavaCourseFactory();

        factory.createNote().edit();
        factory.createVideo().record();

    }

}
