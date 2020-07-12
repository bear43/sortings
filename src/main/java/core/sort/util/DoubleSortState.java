package core.sort.util;

import core.sort.interfaces.ISortState;

public class DoubleSortState <ObjectType extends Number & Comparable<ObjectType>> implements ISortState<ObjectType> {

    private final ObjectType[] array;
    private final int leftIndex;
    private final int rightIndex;

    public DoubleSortState(final ObjectType[] array, final int leftIndex, final int rightIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.array = array;
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
        throw new UnsupportedOperationException("There is no middle index");
    }

    @Override
    public Object[] getAllInfo() {
        return new Object[] {
                leftIndex,
                rightIndex
        };
    }

    @Override
    public ObjectType[] getSortingArray() {
        return array;
    }
}
