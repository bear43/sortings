package core.tree;

import lombok.Data;

import java.util.function.Consumer;

@Data
public class SimpleTree <DataType extends Comparable<DataType>> implements ITree<DataType> {

    private final DataType data;
    private SimpleTree<DataType> leftPart;
    private SimpleTree<DataType> rightPart;

    public SimpleTree(DataType data) {
        this.data = data;
    }

    public ITree<DataType> insertAsLeftPart(DataType data) {
        if(leftPart != null) {
            return leftPart.insert(data);
        } else {
            leftPart = new SimpleTree<>(data);
            return leftPart;
        }
    }

    public ITree<DataType> insertAsRightPart(DataType data) {
        if(rightPart != null) {
            return rightPart.insert(data);
        } else {
            rightPart = new SimpleTree<>(data);
            return rightPart;
        }
    }

    @Override
    public ITree<DataType> insert(DataType data) {
        if(data.compareTo(this.data) < 0) {
            return insertAsLeftPart(data);
        } else {
            return insertAsRightPart(data);
        }
    }

    @Override
    public void traverse(Consumer<DataType> visitorFunc) {
        if(leftPart != null) {
            leftPart.traverse(visitorFunc);
        }
        visitorFunc.accept(data);
        if(rightPart != null) {
            rightPart.traverse(visitorFunc);
        }
    }
}
