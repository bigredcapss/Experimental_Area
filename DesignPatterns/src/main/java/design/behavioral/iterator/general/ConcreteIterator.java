package design.behavioral.iterator.general;

import java.util.List;

/**
 * 具体迭代器----提供具体的元素遍历行为
 * @author BigRedCaps
 * @date 2021/1/3 15:40
 */
public class ConcreteIterator<E> implements Iterator<E>
{
    private List<E> list;
    private int cursor = 0;

    public ConcreteIterator(List<E> list){
        this.list = list;
    }

    @Override
    public E next ()
    {
        return this.list.get(this.cursor++);
    }

    @Override
    public boolean hasNext ()
    {
        return this.cursor < this.list.size();
    }
}
