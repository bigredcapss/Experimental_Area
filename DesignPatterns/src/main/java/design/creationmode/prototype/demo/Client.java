package design.creationmode.prototype.demo;

/**
 * 应用层测试
 */
public class Client {
    public static void main(String[] args) {

//        ExamPaper examPaper = new ExamPaper();
//        System.out.println(examPaper);

        ExamPaper examPaper = (ExamPaper)BeanUtils.copy(new ExamPaper());
        System.out.println(examPaper);
    }
}
