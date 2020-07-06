package core.sort.insertion;

import core.sort.AbstractSort;
import core.sort.interfaces.ISortState;
import core.sort.interfaces.SortType;
import core.sort.util.SlowSort;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@SlowSort
public class InsertionSort <ObjectType extends Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    public InsertionSort() {
        super(SortType.SLOW);
    }

    public InsertionSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(SortType.SLOW, subscribers);
    }

    @Override
    public String getName() {
        return "Insertion";
    }

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        if(unsortedArray.length > 1) {
            for(int keyIndex = startIndex+1; keyIndex <= endIndex; keyIndex++) {
                ObjectType key = unsortedArray[keyIndex];
                int lowerIndex = keyIndex-1;
                while(lowerIndex >= 0 && unsortedArray[lowerIndex].compareTo(key) > 0) {
                    unsortedArray[lowerIndex+1] = unsortedArray[lowerIndex];
                    lowerIndex--;
                }
                unsortedArray[lowerIndex+1] = key;
            }
        }
    }
}
