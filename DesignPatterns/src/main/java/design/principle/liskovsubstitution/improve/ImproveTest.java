package design.principle.liskovsubstitution.improve;

/**
 * @Description:符合里氏替换原则的测试类
 *
 * 这里无论是正方形或是长方形，都通过实现四边形接口，来避免继承泛滥，造成违反里氏替换原则的问题,避免了潜在的正方形死循环
 * 的风险.
 * @Author:BigRedCaps
 */
public class ImproveTest
{
    public static void resize(Rectangle rectangle)
    {
        while(rectangle.getWidth() >= rectangle.getHeight())
        {
            rectangle.setHeight(rectangle.getHeight()+1);
            System.out.println("Width:"+rectangle.getWidth()+" "+ "Heigth:"+rectangle.getHeight());
        }
        System.out.println("Resize End,Width:"+rectangle.getWidth()+" "+"Height:"+rectangle.getHeight());
    }

    public static void main (String[] args)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(15);
        resize(rectangle);
    }
}
