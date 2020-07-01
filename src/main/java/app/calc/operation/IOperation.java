package app.calc.operation;

public interface IOperation {
    boolean mayHandle(String expression);
    Number apply(String expression);
}
