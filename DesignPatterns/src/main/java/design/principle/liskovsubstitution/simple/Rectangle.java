package design.principle.liskovsubstitution.simple;

/**
 * @Description:长方形
 * @Author:BigRedCaps
 */
public class Rectangle
{
    private long height;
    private long width;

    public long getHeight ()
    {
        return height;
    }

    public void setHeight (long height)
    {
        this.height = height;
    }

    public long getWidth ()
    {
        return width;
    }

    public void setWidth (long width)
    {
        this.width = width;
    }
}
