package core.sort.insertion;

import core.sort.ISort;
import org.springframework.stereotype.Component;

@Component
public class InsertionSort <ObjectType extends Comparable<ObjectType>> implements ISort<ObjectType> {

    @Override
    public String getName() {
        return "Insertion";
    }

    @Override
    public void sort(ObjectType[] unsortedArray) {
        if(unsortedArray.length > 1) {
            for(int keyIndex = 1; keyIndex < unsortedArray.length; keyIndex++) {
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
