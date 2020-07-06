package core.tree;

import java.util.function.Consumer;

public interface ITree <DataType> {
    DataType getData();
    ITree<DataType> insert(DataType data);
    void traverse(Consumer<DataType> visitorFunc);
}
