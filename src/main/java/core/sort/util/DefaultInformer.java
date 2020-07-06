package core.sort.util;

import core.sort.interfaces.ISortState;
import core.sort.interfaces.ISortStateInformer;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class DefaultInformer implements ISortStateInformer {

    protected List<Consumer<ISortState>> subscribers = new ArrayList<>();

    @Override
    public void onStateChange(ISortState stage) {
        subscribers.forEach(subscriber -> subscriber.accept(stage));
    }

    @Override
    public void subscribe(@NonNull Consumer<ISortState> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(@NonNull Consumer<ISortState> subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void subscribe(List<Consumer<ISortState>> subscribers) {
        this.subscribers.addAll(subscribers);
    }

    @Override
    public void unsubscribe(List<Consumer<ISortState>> subscribers) {
        this.subscribers.removeAll(subscribers);
    }
}
