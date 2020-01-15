package ru.eisemenov.sort_file_content.processing;

import java.util.List;
import java.util.function.BiFunction;

class InsertionSort {
    static <T extends Comparable<T>> void sort(List<T> list, boolean ascending) {
        if (list == null || list.size() <= 1) return;

        BiFunction<T, T, Boolean> comparator;
        if (ascending) {
            comparator = (T insertedElem, T comparedElem) -> insertedElem.compareTo(comparedElem) <= 0;
        } else {
            comparator = (T insertedElem, T comparedElem) -> insertedElem.compareTo(comparedElem) >= 0;
        }

        for (int insertedElemIndex = 1; insertedElemIndex <= list.size() - 1; insertedElemIndex++) {
            T insertedElem = list.remove(insertedElemIndex);
            elemInsertion(list, insertedElemIndex - 1, insertedElem, comparator);
        }
    }

    private static <T extends Comparable> void elemInsertion(
            List<T> list,
            int sortedPartRightBoundIndex,
            T insertedElem,
            BiFunction<T, T, Boolean> comparator
    ) {
        for (int i = 0; i <= sortedPartRightBoundIndex; i++) {
            if (comparator.apply(insertedElem, list.get(i))) {
                list.add(i, insertedElem);
                return;
            }
        }
        list.add(sortedPartRightBoundIndex + 1, insertedElem);
    }
}
