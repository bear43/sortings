package core.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ExtendWith(TimingExtension.class)
@SpringBootTest
class SortTest {

    private static final Integer EVEN_ARRAY_SIZE = 1000;

    private static final Integer ODD_ARRAY_SIZE = EVEN_ARRAY_SIZE + 1;

    private Integer[] evenOriginalArray = new Integer[EVEN_ARRAY_SIZE];

    private Integer[] evenUnsortedArray = new Integer[EVEN_ARRAY_SIZE];

    private Integer[] oddOriginalArray = new Integer[ODD_ARRAY_SIZE];

    private Integer[] oddUnsortedArray = new Integer[ODD_ARRAY_SIZE];

    private static final Logger logger = Logger.getLogger("SORTING");

    @Autowired
    private List<ISort<Integer>> sortAlgs;

    private static void assertSorted(Integer[] array, Integer[] originalArray) {
        for(int i = 0; i < array.length; i++) {
            if(!array[i].equals(originalArray[i])) {
                throw new AssertionFailedError("Array should be sorted");
            }
        }
    }

    private void generateArrays(final Integer[] originalArray, final Integer[] unsortedArray, final Integer size) {
        for(int i = 0; i < size; i++) {
            originalArray[i] = i;
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(originalArray));
        Collections.shuffle(list);
        Integer[] shuffledArray =  list.toArray(new Integer[0]);
        System.arraycopy(shuffledArray, 0, unsortedArray, 0, size);
    }

    @BeforeAll
    public void beforeAll() {
        generateArrays(evenOriginalArray, evenUnsortedArray, EVEN_ARRAY_SIZE);
        logger.info(String.format("Even array of %d elements", EVEN_ARRAY_SIZE));
        generateArrays(oddOriginalArray, oddUnsortedArray, ODD_ARRAY_SIZE);
        logger.info(String.format("Odd array of %d elements", ODD_ARRAY_SIZE));
    }

    @Test
    @DisplayName("All algs test with EVEN array size")
    public void evenSort() {
        long time;
        for(ISort<Integer> alg : sortAlgs) {
            time = System.currentTimeMillis();
            Integer[] sortedArray = alg.sortWitAllocation(evenUnsortedArray);
            assertSorted(sortedArray, evenOriginalArray);
            time = System.currentTimeMillis() - time;
            logger.info(String.format("Algorithm [%s] took %d ms", alg.getName(), time));
        }
    }

    @Test
    @DisplayName("All algs test with ODD array size")
    public void oddSort() {
        long time;
        for(ISort<Integer> alg : sortAlgs) {
            time = System.currentTimeMillis();
            Integer[] sortedArray = alg.sortWitAllocation(oddUnsortedArray);
            assertSorted(sortedArray, oddOriginalArray);
            time = System.currentTimeMillis() - time;
            logger.info(String.format("Algorithm [%s] took %d ms", alg.getName(), time));
        }
    }

}