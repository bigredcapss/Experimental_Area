package design.behavioral.iterator.course;

/**
 * 自定义抽象迭代器接口
 */
public interface Iterator<E> {
    E next ();
    boolean hasNext ();
}
