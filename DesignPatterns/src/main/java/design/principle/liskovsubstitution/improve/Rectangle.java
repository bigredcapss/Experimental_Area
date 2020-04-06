package design.principle.liskovsubstitution.improve;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class Rectangle implements QuadRangle
{
    private long width;
    private long height;

    public void setWidth (long width)
    {
        this.width = width;
    }

    public void setHeight (long height)
    {
        this.height = height;
    }

    public long getWidth ()
    {
        return width;
    }
    
    public long getHeight ()
    {
        return height;
    }
}
