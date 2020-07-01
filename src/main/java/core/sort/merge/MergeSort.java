package core.sort.merge;

import core.sort.ISort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component
public class MergeSort <ObjectType extends Comparable<ObjectType>> implements ISort<ObjectType> {

    private Class<ObjectType> objectClass;

    @Override
    public String getName() {
        return "Merge";
    }

    private boolean inBound(final int startOffset, final int index, final int size) {
        int realIndex = index - startOffset;
        return realIndex < size;
    }

    private void insert(final ObjectType[] unsortedArray, final ObjectType[] mergedArray, final int startIndex, final int endIndex) {
        final int size = endIndex - startIndex + 1;
        if (size >= 0) System.arraycopy(mergedArray, 0, unsortedArray, startIndex, size);
    }

    @SuppressWarnings("unchecked")
    private void merge(ObjectType[] array, int startIndex, int endIndex) {
        int sub = endIndex - startIndex + 1;
        if(sub > 1) {
            final int middle = (startIndex + endIndex) / 2;
            final int middlePlusOne = middle + 1;
            merge(array, startIndex, middle);
            merge(array, middlePlusOne, endIndex);
            int leftIndex = startIndex;
            final int leftSize = middle - startIndex + 1;
            int rightIndex = middlePlusOne;
            final int rightSize = endIndex - middle;
            boolean isRightInBound = true, isLeftInBound = true;
            ObjectType[] newArray = (ObjectType[]) Array.newInstance(objectClass, leftSize + rightSize);
            int indexInNewArray = 0;
            while(isLeftInBound || isRightInBound) {
                if(isLeftInBound && isRightInBound) {
                    ObjectType leftObject = array[leftIndex];
                    ObjectType rightObject = array[rightIndex];
                    if(leftObject.compareTo(rightObject) < 0) {
                        newArray[indexInNewArray] = leftObject;
                        leftIndex++;
                    } else {
                        newArray[indexInNewArray] = rightObject;
                        rightIndex++;
                    }
                    indexInNewArray++;
                } else if(isLeftInBound) {
                    int maxIndexExclusively = startIndex + leftSize;
                    for(; leftIndex < maxIndexExclusively; leftIndex++, indexInNewArray++) {
                        newArray[indexInNewArray] = array[leftIndex];
                    }
                } else {
                    int maxIndexExclusively = middlePlusOne + rightSize;
                    for(; rightIndex < maxIndexExclusively; rightIndex++, indexInNewArray++) {
                        newArray[indexInNewArray] = array[rightIndex];
                    }
                }
                isLeftInBound = inBound(startIndex, leftIndex, leftSize);
                isRightInBound = inBound(middlePlusOne, rightIndex, rightSize);
            }
            insert(array, newArray, startIndex, endIndex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort(ObjectType[] unsortedArray) {
        if(unsortedArray.length > 1) {
            objectClass = (Class<ObjectType>) unsortedArray[0].getClass();
            merge(unsortedArray, 0, unsortedArray.length - 1);
        }
    }
}
