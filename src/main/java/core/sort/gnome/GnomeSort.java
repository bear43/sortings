package core.sort.gnome;

import core.sort.AbstractSort;
import core.sort.util.SlowSort;
import core.sort.util.Util;
import core.sort.interfaces.ISortState;
import core.sort.interfaces.SortType;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@SlowSort
public class GnomeSort <ObjectType extends Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    public GnomeSort() {
        super(SortType.SLOW);
    }

    public GnomeSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(SortType.SLOW, subscribers);
    }

    @Override
    public String getName() {
        return "Gnome";
    }

    /*
    Гномья сортировка основана на технике, используемой обычным голландским садовым гномом (нидерл. tuinkabouter).
    Это метод, которым садовый гном сортирует линию цветочных горшков.
    По существу он смотрит на текущий и предыдущий садовые горшки: если они в правильном порядке, он шагает на один горшок вперёд,
    иначе он меняет их местами и шагает на один горшок назад.
    Граничные условия:
    если нет предыдущего горшка, он шагает вперёд;
    если нет следующего горшка, он закончил.
    * */

    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        int index = startIndex+1;
        while(index <= endIndex) {
            if(index == 0) {
                index++;
            }
            ObjectType current = unsortedArray[index];
            ObjectType previous = unsortedArray[index-1];
            if(current.compareTo(previous) < 0) {
                Util.swap(unsortedArray, index, index-1);
                index--;
            } else {
                index++;
            }
        }
    }
}
