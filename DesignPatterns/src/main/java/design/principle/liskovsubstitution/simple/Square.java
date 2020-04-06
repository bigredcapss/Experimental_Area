package design.principle.liskovsubstitution.simple;

/**
 * @Description:正方形
 * @Author:BigRedCaps
 */
public class Square extends Rectangle
{
    private long length;

    public long getLength ()
    {
        return length;
    }

    public void setLength (long length)
    {
        this.length = length;
    }

    @Override
    public long getHeight ()
    {
        return getLength();
    }

    @Override
    public void setHeight (long height)
    {
        setLength(height);
    }

    @Override
    public long getWidth ()
    {
        return getLength();
    }

    @Override
    public void setWidth (long width)
    {
        setLength(width);
    }
}
