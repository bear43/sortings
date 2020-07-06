package core.sort;

import core.sort.bubble.BubbleSort;
import core.sort.interfaces.ISort;
import core.sort.util.FastSort;
import core.sort.util.SlowSort;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SortTest {

    private static final Integer EVEN_ARRAY_SIZE = 10;

    private static final Integer ODD_ARRAY_SIZE = EVEN_ARRAY_SIZE + 1;

    private final Integer[] evenOriginalArray = new Integer[EVEN_ARRAY_SIZE];

    private final Integer[] evenUnsortedArray = new Integer[EVEN_ARRAY_SIZE];

    private final Integer[] oddOriginalArray = new Integer[ODD_ARRAY_SIZE];

    private final Integer[] oddUnsortedArray = new Integer[ODD_ARRAY_SIZE];

    private final long sleepTime = 500;

    private static final Logger logger = Logger.getLogger("SORTING");

    @Autowired
    @SlowSort
    private List<AbstractSort<Integer>> slowSortAlgs;

    @Autowired
    @FastSort
    private List<AbstractSort<Integer>> fastSortAlgs;

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

    private void sort(final List<? extends AbstractSort<Integer>> sortAlgs, final Integer[] array, final Integer[] originalArray) {
        long time;
        for(ISort<Integer> alg : sortAlgs) {
            if(alg instanceof BubbleSort) {
                ((BubbleSort<Integer>) alg).subscribe(state -> {
                    logger.info("TEST: I = " + state.getLeftIndex() + "; J = " + state.getRightIndex());
                });
            }
            time = System.currentTimeMillis();
            Integer[] sortedArray = alg.sortWithAllocation(array);
            assertSorted(sortedArray, originalArray);
            time = System.currentTimeMillis() - time;
            logger.info(String.format("Algorithm [%s] took %d ms", alg.getName(), time));
        }
    }

    public void slowEvenSort() {
        sort(slowSortAlgs, evenUnsortedArray, evenOriginalArray);
    }

    public void slowOddSort() {
        sort(slowSortAlgs, oddUnsortedArray, oddOriginalArray);
    }

    @Test
    @DisplayName("Slow algs test with EVEN and ODD array size")
    @Order(0)
    public void slowSort() {
        logger.info("EVEN SORTING");
        slowEvenSort();
        logger.info("ODD SORTING");
        slowOddSort();
    }

    @Test
    @DisplayName("Fast algs test with EVEN and ODD array size")
    @Order(1)
    public void fastSort() {
        logger.info("EVEN SORTING");
        fastEvenSort();
        logger.info("ODD SORTING");
        fastOddSort();
    }


    public void fastEvenSort() {
        sort(fastSortAlgs, evenUnsortedArray, evenOriginalArray);
    }


    public void fastOddSort() {
        sort(fastSortAlgs, oddUnsortedArray, oddOriginalArray);
    }


}