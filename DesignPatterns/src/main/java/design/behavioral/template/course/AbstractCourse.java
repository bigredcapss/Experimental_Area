package design.behavioral.template.course;

/**
 * 抽象课程类
 * @author BigRedCaps
 * @date 2020/12/27 14:29
 */
public abstract class AbstractCourse
{
    public final void createCourse(){
        //1、发布预习资料
        postPreResoucse();

        //2、制作课件
        createPPT();

        //3、直播授课
        liveVideo();

        //4、上传课后资料
        postResource();

        //5、布置作业
        postHomework();

        if(needCheckHomework()){
            checkHomework();
        }
    }

    protected abstract void checkHomework();

    /**
     * 注：设计钩子方法的目的是用来干预执行流程，使得我们控制行为流程更加灵活，使其更加符合实际的业务需求，钩子方法的返回
     * 值一般为适合条件分支语句的boolean类型或者int类型的值
     * @return
     */
    //钩子方法
    protected boolean needCheckHomework(){return  false;}

    protected void postHomework(){
        System.out.println("布置作业");
    }

    protected void postResource(){
        System.out.println("上传课后资料");
    }

    protected void liveVideo(){
        System.out.println("直播授课");
    }

    protected void createPPT(){
        System.out.println("制作课件");
    }

    protected void postPreResoucse(){
        System.out.println("发布预习资料");
    }
}
