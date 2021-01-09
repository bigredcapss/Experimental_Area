package design.behavioral.interpreter.general;

/**
 * 抽象表达式角色---负责定义一个解释方法interpret,交由具体的子类进行具体解释
 */
public interface IExpression {
    // 对表达式进行解释
    Object interpret (Context context);
}