package core.sort.util;

import core.sort.interfaces.ISortState;

public class FullSortState <ObjectType extends Number & Comparable<ObjectType>> implements ISortState<ObjectType> {

    private final ObjectType[] array;
    private final int leftIndex;
    private final int rightIndex;
    private final int middleIndex;

    public FullSortState(final ObjectType[] array, final int leftIndex, final int rightIndex, final int middleIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.middleIndex = middleIndex;
        this.array = array;
    }

    public FullSortState(final ObjectType[] array, final int leftIndex, final int rightIndex) {
        this(array, leftIndex, rightIndex, -1);
    }


    @Override
    public int getLeftIndex() {
        return leftIndex;
    }

    @Override
    public int getRightIndex() {
        return rightIndex;
    }

    @Override
    public int getMiddleIndex() {
        return middleIndex;
    }

    @Override
    public Object[] getAllInfo() {
        return new Object[] {
                leftIndex,
                rightIndex,
                middleIndex
        };
    }

    @Override
    public ObjectType[] getSortingArray() {
        return array;
    }
}
