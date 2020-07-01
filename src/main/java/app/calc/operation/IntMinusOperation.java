package app.calc.operation;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IntMinusOperation extends IntOperation {

    private static final String SPLIT_PATTERN = "\\-";
    private final String EXPRESSION_PATTERN = "[0-9]*-[0-9]*";

    @Override
    public boolean mayHandle(@NonNull String expression) {
        Pattern pattern = Pattern.compile(EXPRESSION_PATTERN);
        return pattern.matcher(expression).matches();
    }

    @Override
    public Number apply(@NonNull String expression) {
        Integer[] operands = extractNumbers(expression, SPLIT_PATTERN);
        return operands[0] - operands[1];
    }
}
