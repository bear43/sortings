package core.sort.gnome;

import core.sort.ISort;
import core.sort.Util;
import org.springframework.stereotype.Component;


@Component
public class OptimizedGnomeSort <ObjectType extends Comparable<ObjectType>> implements ISort<ObjectType> {
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
    public void sort(ObjectType[] unsortedArray) {
        int lastUnequal = -1;
        int index = 1;
        while(index < unsortedArray.length) {
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
