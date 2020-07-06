package core.sort.tree;

import core.sort.AbstractSort;
import core.sort.interfaces.ISortState;
import core.sort.interfaces.SortType;
import core.sort.util.FastSort;
import core.tree.ITree;
import core.tree.SimpleTree;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@FastSort
public class TreeSort <ObjectType extends Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    private int index;

    public TreeSort() {
        super(SortType.FAST);
    }

    public TreeSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(SortType.FAST, subscribers);
    }

    @Override
    public String getName() {
        return "Tree";
    }

    private void insertIntoArrayRelativeToRightIndex(ObjectType[] array, ObjectType data) {
        array[index++] = data;
    }

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        if (unsortedArray.length > 1) {
            ITree<ObjectType> rootNode = new SimpleTree<>(unsortedArray[startIndex]);
            for(int index = startIndex+1; index <= endIndex; index++) {
                rootNode.insert(unsortedArray[index]);
            }
            index = 0;
            rootNode.traverse((data) -> {
                insertIntoArrayRelativeToRightIndex(unsortedArray, data);
            });
        }
    }
}
