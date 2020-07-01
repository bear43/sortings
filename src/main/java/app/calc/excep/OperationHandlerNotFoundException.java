package app.calc.excep;

public class OperationHandlerNotFoundException extends RuntimeException {
    public OperationHandlerNotFoundException() {
        super("Operation handler not found");
    }

    public OperationHandlerNotFoundException(String expression) {
        super("Operation handler for expression: '" + expression + "' not found");
    }
}
