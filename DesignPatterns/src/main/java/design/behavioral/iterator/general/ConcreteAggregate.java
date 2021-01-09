package design.behavioral.iterator.general;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体容器---创建具体迭代器
 * @author BigRedCaps
 * @date 2021/1/3 15:38
 */
public class ConcreteAggregate<E> implements IAggregate<E>
{
    private List<E> list = new ArrayList<>();

    @Override
    public boolean add (E element)
    {
        return this.list.add(element);
    }

    @Override
    public boolean remove (E element)
    {
        return this.list.remove(element);
    }

    @Override
    public Iterator<E> iterator ()
    {
        return new ConcreteIterator<E>(this.list);
    }
}
