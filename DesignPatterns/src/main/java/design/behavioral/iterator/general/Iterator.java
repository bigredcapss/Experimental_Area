package design.behavioral.iterator.general;

/**
 * 抽象迭代器角色---抽象迭代器负责定义访问和遍历元素的接口
 * @author BigRedCaps
 * @date 2021/1/3 15:32
 */
public interface Iterator<E>
{
    E next();

    boolean hasNext();
}
