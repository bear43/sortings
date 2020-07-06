package core.sort;

import core.sort.interfaces.ISortState;
import core.sort.interfaces.ISortStateInformer;
import core.sort.interfaces.ISubscribableSort;
import core.sort.interfaces.SortType;
import core.sort.util.DefaultInformer;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.function.Consumer;

@Data
public abstract class AbstractSort <ObjectType extends Comparable<ObjectType>> implements ISubscribableSort<ObjectType> {
    protected final SortType type;
    protected final ISortStateInformer informer;

    public AbstractSort(@NonNull SortType type, @NonNull ISortStateInformer informer) {
        this.type = type;
        this.informer = informer;
    }

    public AbstractSort(@NonNull SortType type, @NonNull List<Consumer<ISortState>> subscribers, @NonNull ISortStateInformer informer) {
        this.type = type;
        this.informer = informer;
        informer.subscribe(subscribers);
    }

    public AbstractSort(@NonNull SortType type) {
        this(type, new DefaultInformer());
    }

    public AbstractSort(@NonNull SortType type, @NonNull List<Consumer<ISortState>> subscribers) {
        this(type, subscribers, new DefaultInformer());
    }

    @Override
    public void subscribe(@NonNull Consumer<ISortState> subscriber) {
        informer.subscribe(subscriber);
    }

    @Override
    public void unsubscribe(@NonNull Consumer<ISortState> subscriber) {
        informer.unsubscribe(subscriber);
    }
}
