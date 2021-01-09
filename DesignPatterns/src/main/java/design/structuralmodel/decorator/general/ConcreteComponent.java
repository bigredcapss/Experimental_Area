package design.structuralmodel.decorator.general;

/**
 * 具体组件----实现/继承Component的一个具体对象；也即被装饰对象
 * @author BigRedCaps
 * @date 2020/12/30 21:54
 */
public class ConcreteComponent extends Component
{
    @Override
    public void operation ()
    {
        //相应的功能处理
        System.out.println("处理业务逻辑");
    }
}
