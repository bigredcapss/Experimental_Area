package design.behavioral.interpreter.general;

/**
 * 非终结符表达式角色---实现文法中与非终结符有关的解释操作。文法中的每条规则都对应于一个非终结符表达式。非终结符表达式一般
 * 是文法中的运算符或者其它关键字，比如公式R=R1+R2中，“+”就是非终结符，解析“+”的解释器就是非终结符表达式，非终结符表
 * 达式根据逻辑上的复杂程度而增加，原则上每个文法规则都对应一个非终结符表达式
 */
public class NonterminalExpression implements IExpression {
    private IExpression [] expressions;

    public NonterminalExpression(IExpression... expressions) {
        // 每个非终结符表达式都会对其他表达式产生依赖
        this.expressions = expressions;
    }


    public Object interpret(Context context) {
        // 进行文法处理
        context.put("","");
        return null;
    }
}