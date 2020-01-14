package ru.eisemenov.sort_file_content.processing;

import java.util.List;

class InsertionSort {

    static <T extends Comparable> List<T> sort(List<T> list, boolean ascending) {
        // TODO implement insertion sort method

        list.sort((o1, o2) -> ascending ? o1.compareTo(o2) : o2.compareTo(o1));

        return list;
    }
}
