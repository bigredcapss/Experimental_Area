package design.behavioral.iterator.general;

/**
 * 抽象容器---负责定义提供具体迭代器的接口
 * @author BigRedCaps
 * @date 2021/1/3 15:34
 */
public interface IAggregate<E>
{
    boolean add(E element);

    boolean remove(E element);

    Iterator<E> iterator();
}
