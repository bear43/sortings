package core.sort.bubble;

import core.sort.AbstractSort;
import core.sort.interfaces.ISortStateInformer;
import core.sort.util.DoubleSortState;
import core.sort.util.SlowSort;
import core.sort.util.Util;
import core.sort.interfaces.ISortState;
import core.sort.interfaces.SortType;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@SlowSort
public class BubbleSort<ObjectType extends Number & Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    public BubbleSort(ISortStateInformer informer) {
        super(SortType.SLOW, informer);
    }

    public BubbleSort(ISortStateInformer informer, List<Consumer<ISortState>> subscribers) {
        super(SortType.SLOW, subscribers, informer);
    }

    public BubbleSort() {
        super(SortType.SLOW);
    }

    public BubbleSort(List<Consumer<ISortState>> subscribers) {
        super(SortType.SLOW, subscribers);
    }

    @Override
    public String getName() {
        return "Bubble";
    }

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        for(int i = startIndex; i <= endIndex; i++) {
            for(int j = startIndex; j < endIndex-i; j++) {
                informer.onStateChange(new DoubleSortState(unsortedArray, j, j+1));
                if(unsortedArray[j].compareTo(unsortedArray[j+1]) > 0) {
                    Util.swap(unsortedArray, j, j+1);
                    informer.onStateChange(new DoubleSortState(unsortedArray,j+1, j));
                }
            }
        }
    }
}
