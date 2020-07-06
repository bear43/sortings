package core.sort.interfaces;

public interface ITypedSort <ObjectType extends Comparable<ObjectType>> extends ISort<ObjectType> {
    SortType getType();
}
