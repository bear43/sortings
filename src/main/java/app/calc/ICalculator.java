package app.calc;

public interface ICalculator <ResultType extends Number> {
    ResultType convert(Number number);
    ResultType calc(String expression);
}
