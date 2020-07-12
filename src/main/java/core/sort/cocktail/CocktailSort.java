package core.sort.cocktail;

import core.sort.util.DoubleSortState;
import core.sort.util.SlowSort;
import core.sort.util.Util;
import core.sort.bubble.BubbleSort;
import core.sort.interfaces.ISortState;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@SlowSort
public class CocktailSort<ObjectType extends Number & Comparable<ObjectType>> extends BubbleSort<ObjectType> {

    public CocktailSort() {
    }

    public CocktailSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(subscribers);
    }

    @Override
    public String getName() {
        return "Cocktail";
    }

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        int leftBorder = startIndex, rightBorder = endIndex;
        while(leftBorder < rightBorder) {
            for(int index = leftBorder; index < rightBorder; index++) {
                informer.onStateChange(new DoubleSortState(unsortedArray, index, index+1));
                if(unsortedArray[index].compareTo(unsortedArray[index+1]) > 0) {
                    Util.swap(unsortedArray, index, index+1);
                    informer.onStateChange(new DoubleSortState(unsortedArray, index+1, index));
                }
            }
            rightBorder--;
            for(int index = rightBorder; index > leftBorder; index--) {
                informer.onStateChange(new DoubleSortState(unsortedArray, index-1, index));
                if(unsortedArray[index].compareTo(unsortedArray[index-1]) < 0) {
                    Util.swap(unsortedArray, index, index-1);
                    informer.onStateChange(new DoubleSortState(unsortedArray, index, index-1));
                }
            }
            leftBorder++;
        }
    }
}
