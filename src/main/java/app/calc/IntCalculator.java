package app.calc;

import app.calc.excep.OperationHandlerNotFoundException;
import app.calc.operation.IOperation;
import app.calc.operation.IntMinusOperation;
import app.calc.operation.IntPlusOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class IntCalculator implements ICalculator<Integer> {

    @Autowired
    private List<IOperation> operations;

    private Optional<IOperation> handlerLookup(String expression) {
        return operations.stream().filter(operation -> operation.mayHandle(expression)).findFirst();
    }

    @Override
    public Integer convert(Number number) {
        return number.intValue();
    }

    @Override
    public Integer calc(String expression) {
        Number result = handlerLookup(expression).orElseThrow(() -> new OperationHandlerNotFoundException(expression)).apply(expression);
        return result instanceof Integer ? (Integer) result : convert(result);
    }
}
