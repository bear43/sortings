package core.sort.interfaces;

import java.util.List;
import java.util.function.Consumer;

public interface ISortStateInformer {
    void onStateChange(ISortState stage);
    void subscribe(Consumer<ISortState> subscriber);
    void unsubscribe(Consumer<ISortState> subscriber);
    void subscribe(List<Consumer<ISortState>> subscribers);
    void unsubscribe(List<Consumer<ISortState>> subscribers);
}
