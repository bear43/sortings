package core.sort.bubble;

import core.sort.ISort;
import core.sort.Util;
import org.springframework.stereotype.Component;

@Component
public class BubbleSort<ObjectType extends Comparable<ObjectType>> implements ISort<ObjectType> {

    @Override
    public String getName() {
        return "Bubble";
    }

    @Override
    public void sort(ObjectType[] unsortedArray) {
        for(int i = 0; i < unsortedArray.length-1; i++) {
            for(int j = 0; j < unsortedArray.length-1-i; j++) {
                if(unsortedArray[j].compareTo(unsortedArray[j+1]) > 0) {
                    Util.swap(unsortedArray, j, j+1);
                }
            }
        }
    }
}
