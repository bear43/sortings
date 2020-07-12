package core.sort.interfaces;

public interface ISortState <ObjectType extends Number & Comparable<ObjectType>> {
    ObjectType[] getSortingArray();
    int getLeftIndex();
    int getRightIndex();
    int getMiddleIndex();
    Object[] getAllInfo();
}
