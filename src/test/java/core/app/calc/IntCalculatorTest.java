package core.app.calc;

import app.calc.ICalculator;
import app.calc.excep.OperationHandlerNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntCalculatorTest {

    private final ICalculator<Integer> integerCalculator;

    @Autowired
    IntCalculatorTest(ICalculator<Integer> integerCalculator) {
        this.integerCalculator = integerCalculator;
    }

    @DisplayName("converting test")
    @Test
    void convert() {
    }

    @DisplayName("calc equals")
    @Test
    void calcEquals() {
        Integer expected = 4;
        Integer result = integerCalculator.calc("2+2");
        assertEquals(expected, result);
    }

    @DisplayName("calc not equals")
    @Test
    void calcNotEquals() {
        Integer expected = 2;
        Integer result = integerCalculator.calc("2+2");
        assertNotEquals(expected, result);
    }

    @DisplayName("calc throws")
    @Test
    void calcThrows() {
        assertThrows(OperationHandlerNotFoundException.class, () -> {
            integerCalculator.calc("2*2");
        });
    }
}