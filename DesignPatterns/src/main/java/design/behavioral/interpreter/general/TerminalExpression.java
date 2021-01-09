package design.behavioral.interpreter.general;

/**
 * 终结符表达式角色---实现文法中与终结符有关的解释操作。文法中的每一个终结符都有一个具体的终结表达式与之对应，比如公式
 * R=R1+R2,R1和R2就是终结符，对应的解析R1与R2的解释器就是终结符表达式。通常一个解释器模式中只有一个终结符表达式，但有
 * 多个实例，对应不同的终结符（R1,R2)
 */
public class TerminalExpression implements IExpression {

    private Object value;

    public Object interpret(Context context) {
        // 实现文法中与终结符有关的操作
        context.put("","");
        return null;
    }

}