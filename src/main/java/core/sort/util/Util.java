package core.sort.util;

import java.util.function.Predicate;

public class Util {

    public static <ObjectType extends Comparable<ObjectType>> void swap(ObjectType[] array, int firstIndex, int secondIndex) {
        ObjectType savedValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = savedValue;
    }
}
