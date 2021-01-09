package design.behavioral.command.general;

/**
 * 命令模式测试类
 *
 * 可以自行查看UML类图，通过UML类图可以很清晰的看到：ICommand的出现就是作为Receiver和Invoker的中间件，解耦了彼此。而之所以
 * 引入ICommand中间件，可能是以下两方面的原因：
 * 一方面：解耦请求与实现：即解耦了Invoker和Receiver，因为在UML类图中，Invoker是一个具体的实现，等待接收客户端传入命令（
 * 即Invoker与客户端耦合）,Invoker处于业务逻辑区域，应当是一个稳定的结构。而Receiver是属于业务功能模块，是经常变动的，如果
 * 没有Command，则Invoker紧耦合Receiver，一个稳定的结构依赖了一个不稳定的结构，就会导致整个结构都不稳定了。这就是Command
 * 引入的原因：不仅仅解耦请求与实现，同时稳定Invoker依赖稳定Command,结构还是稳定的。
 *
 * 扩展性增强：扩展性体现在两个方面：
 * 1. Receiver属于底层细节，可以通过更换不同的Receiver达到不同的细节实现；
 * 2. Command接口本身就是抽象的，本身就具备扩展；而且由于命令对象本身就具备抽象，如果结合装饰器模式，功能扩展更加方便；
 *
 * 在一个系统中，不同的命令对应不同的请求，也就是说无法把请求抽象化，因此命令模式中的Receiver是具体实现；但是如果在某一个
 * 模块中，可以对Receiver进行抽象，其实这就变相使用到了桥接模式（Command类具备两个变化的维度：Command和Receiver)，这样子的
 * 扩展性会更加优秀；
 *
 *
 * @author BigRedCaps
 * @date 2021/1/3 16:24
 */
public class Test
{
    public static void main (String[] args)
    {
        ICommand iCommand = new ConcreteCommand();
        Invoker invoker = new Invoker(iCommand);
        invoker.action();
    }
}
