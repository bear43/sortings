package core.sort.interfaces;

import java.util.Arrays;

public interface ISort<ObjectType extends Comparable<ObjectType>> {

    default String getName() {
        return "Undefined";
    }

    default ObjectType[] sortWithAllocation(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        ObjectType[] copiedArray = Arrays.copyOfRange(unsortedArray, startIndex, endIndex+1);
        sort(copiedArray, 0, copiedArray.length-1);
        return copiedArray;
    }

    default ObjectType[] sortWithAllocation(ObjectType[] unsortedArray, int startIndex) {
        return sortWithAllocation(unsortedArray, startIndex, unsortedArray.length-1);
    }

    default ObjectType[] sortWithAllocation(ObjectType[] unsortedArray) {
        return sortWithAllocation(unsortedArray, 0);
    }

    void sort(ObjectType[] unsortedArray, int startIndex, int endIndex);

    default void sort(ObjectType[] unsortedArray, int startIndex) {
        sort(unsortedArray, 0, unsortedArray.length-1);
    }

    default void sort(ObjectType[] unsortedArray) {
        sort(unsortedArray, 0);
    }
}
