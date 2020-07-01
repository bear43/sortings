package core.sort.cocktail;

import core.sort.Util;
import core.sort.bubble.BubbleSort;
import org.springframework.stereotype.Component;

@Component
public class CocktailSort<ObjectType extends Comparable<ObjectType>> extends BubbleSort<ObjectType> {

    @Override
    public String getName() {
        return "Cocktail";
    }

    @Override
    public void sort(ObjectType[] unsortedArray) {
        int leftBorder = 0, rightBorder = unsortedArray.length-1;
        while(leftBorder < rightBorder) {
            for(int index = leftBorder; index < rightBorder; index++) {
                if(unsortedArray[index].compareTo(unsortedArray[index+1]) > 0) {
                    Util.swap(unsortedArray, index, index+1);
                }
            }
            rightBorder--;
            for(int index = rightBorder; index > leftBorder; index--) {
                if(unsortedArray[index].compareTo(unsortedArray[index-1]) < 0) {
                    Util.swap(unsortedArray, index, index-1);
                }
            }
            leftBorder++;
        }
    }
}
