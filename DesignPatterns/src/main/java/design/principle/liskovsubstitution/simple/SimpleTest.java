package design.principle.liskovsubstitution.simple;

/**
 * @Description:不符合里氏替换原则的代码测试
 * @Author:BigRedCaps
 */
public class SimpleTest
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
        //这里resize方法如果把Rectangle对象替换成Square对象,将会陷入死循环，不符合里氏替换原则
    }
}
