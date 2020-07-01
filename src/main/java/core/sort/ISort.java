package core.sort;

import java.util.Arrays;

public interface ISort<ObjectType extends Comparable<ObjectType>> {

    default String getName() {
        return "Undefined";
    }

    default ObjectType[] sortWitAllocation(ObjectType[] unsortedArray) {
        ObjectType[] copiedArray = Arrays.copyOf(unsortedArray, unsortedArray.length);
        sort(copiedArray);
        return copiedArray;
    }
    void sort(ObjectType[] unsortedArray);
}
