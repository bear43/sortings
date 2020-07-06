package core.sort.quick;

import core.sort.AbstractSort;
import core.sort.util.FastSort;
import core.sort.util.Util;
import core.sort.interfaces.ISortState;
import core.sort.interfaces.SortType;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@FastSort
public class QuickSort <ObjectType extends Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    public QuickSort() {
        super(SortType.FAST);
    }

    public QuickSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(SortType.FAST, subscribers);
    }

    @Override
    public String getName() {
        return "Quick";
    }

    /*
    Алгоритм состоит из трёх шагов:

1. Выбрать элемент из массива. Назовём его опорным.
2. Разбиение: перераспределение элементов в массиве таким образом,
что элементы меньше опорного помещаются перед ним, а больше или равные после.
3. Рекурсивно применить первые два шага к двум подмассивам слева и справа от опорного элемента.
Рекурсия не применяется к массиву, в котором только один элемент или отсутствуют элементы.
    */

    private void qsort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        final int size = endIndex - startIndex + 1;
        if(size > 1) {
            int pivotIndex = (startIndex+endIndex) / 2;
            final ObjectType pivot = unsortedArray[pivotIndex];
            int leftIndex = startIndex - 1, rightIndex = endIndex + 1;
            while(true) {
                do {
                    leftIndex++;
                } while(leftIndex <= endIndex && unsortedArray[leftIndex].compareTo(pivot) < 0);
                do {
                    rightIndex--;
                } while(rightIndex >= startIndex && unsortedArray[rightIndex].compareTo(pivot) > 0);
                if(leftIndex >= rightIndex) {
                    pivotIndex = rightIndex;
                    break;
                }
                Util.swap(unsortedArray, leftIndex, rightIndex);
            }
            qsort(unsortedArray, startIndex, pivotIndex);
            qsort(unsortedArray, pivotIndex+1, endIndex);
        }
    }

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        qsort(unsortedArray, startIndex, endIndex);
    }
}
