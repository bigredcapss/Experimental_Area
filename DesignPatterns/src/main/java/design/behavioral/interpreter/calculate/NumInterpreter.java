package design.behavioral.interpreter.calculate;

/**
 * 数字表达式类
 */
public class NumInterpreter implements IArithmeticInterpreter {
    private int value;

    public NumInterpreter(int value) {
        this.value = value;
    }


    public int interpret() {
        return this.value;
    }
}