package core.sort.interfaces;

import java.util.function.Consumer;

public interface ISubscribableSort<ObjectType extends Comparable<ObjectType>> extends ITypedSort<ObjectType> {
    default void subscribe(Consumer<ISortState> subscriber) {

    }

    default void unsubscribe(Consumer<ISortState> subscriber) {

    }
}
