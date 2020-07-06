package core.sort.util;

import core.sort.interfaces.ISortState;

public class FullSortState implements ISortState {

    private final int leftIndex;
    private final int rightIndex;
    private final int middleIndex;

    public FullSortState(final int leftIndex, final int rightIndex, final int middleIndex) {
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.middleIndex = middleIndex;
    }

    public FullSortState(final int leftIndex, final int rightIndex) {
        this(leftIndex, rightIndex, -1);
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
}
