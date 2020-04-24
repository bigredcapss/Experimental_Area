package design.creationmode.builder.simple;

/**
 * 课程建造
 */
public class CourseBuilder{

    private Course course = new Course();

    //建造课程对象需要加入课程名字，课程PPT,课程视频，课程笔记，课程作业等步骤
    //不分先后顺序，最终都是构造课程这一对象
    public void addName(String name) {
        course.setName(name);
    }
    
    public void addPPT(String ppt) {
        course.setPpt(ppt);
    }
    
    public void addVideo(String video) {
        course.setVideo(video);
    }
    
    public void addNote(String note) {
        course.setNote(note);
    }
    
    public void addHomework(String homework) {
        course.setHomework(homework);
    }
    
    public Course build() {
        return course;
    }
}
