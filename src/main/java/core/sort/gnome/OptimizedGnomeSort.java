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
public class OptimizedGnomeSort <ObjectType extends Comparable<ObjectType>> extends AbstractSort<ObjectType> {

    public OptimizedGnomeSort() {
        super(SortType.SLOW);
    }

    public OptimizedGnomeSort(@NonNull List<Consumer<ISortState>> subscribers) {
        super(SortType.SLOW, subscribers);
    }

    @Override
    public String getName() {
        return "Optimized gnome";
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

    /*
     * В силу того, что после перехода влево на n горшков до первой пары, которая соблюдает условие: предыдущий < текущего,
     * Обычный алгоритм начинает увеличивать текущий индекс и снова проходить по уже проверенным парам, его можно улучшить
     * Сохраняя индекс первой пары, где условие: предыдущий < текущего не выполняется, чтобы потом вернуться на этот индекс
     * */


    @Override
    public void sort(ObjectType[] unsortedArray, int startIndex, int endIndex) {
        int lastUnequal = -1;
        int index = startIndex+1;
        while(index <= endIndex) {
            if(index == 0) {
                index = lastUnequal == -1 ? 1 : lastUnequal;
            }
            ObjectType current = unsortedArray[index];
            ObjectType previous = unsortedArray[index-1];
            if(current.compareTo(previous) < 0) {
                Util.swap(unsortedArray, index, index-1);
                if(lastUnequal == -1) {
                    lastUnequal = index;
                }
                index--;
            } else {
                if(lastUnequal != -1) {
                    index = lastUnequal;
                    lastUnequal = -1;
                } else {
                    index++;
                }
            }
        }
    }
}
