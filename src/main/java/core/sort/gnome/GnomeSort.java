package core.sort.gnome;

import core.sort.ISort;
import core.sort.Util;
import org.springframework.stereotype.Component;

@Component
public class GnomeSort <ObjectType extends Comparable<ObjectType>> implements ISort<ObjectType> {

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
    public void sort(ObjectType[] unsortedArray) {
        int index = 1;
        while(index < unsortedArray.length) {
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
