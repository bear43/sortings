package app.calc.operation;

public abstract class IntOperation implements IOperation {
    protected final String INTEGER_PATTERN = "[0-9]*";

    protected Integer[] extractNumbers(String expression, final String pattern) {
        String[] operands = expression.split(pattern);
        if(operands.length == 2) {
            return new Integer[] {
                    Integer.parseInt(operands[0]),
                    Integer.parseInt(operands[1])
            };
        } else {
            throw new IllegalArgumentException("Expected expression in a+b format where a and b is ints");
        }
    }
}
