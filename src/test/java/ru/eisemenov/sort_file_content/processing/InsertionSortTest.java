package ru.eisemenov.sort_file_content.processing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    @DisplayName("sortNullList")
    void sortNullList() {
        ArrayList<Integer> list = null;

        InsertionSort.sort(list, true);

        assertNull(list);
    }

    @Test
    @DisplayName("sortEmptyList")
    void sortEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        InsertionSort.sort(list, true);

        assertEquals(0, list.size());
    }

    @Test
    void sortOneElementList() {
        ArrayList<Integer> list = new ArrayList<>(Collections.singletonList(1));
        InsertionSort.sort(list, true);

        assertArrayEquals(new Integer[]{1}, list.toArray());
    }

    @Test
    void sortAscendingTwoElementsList() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(2, 1));

        InsertionSort.sort(list1, true);
        InsertionSort.sort(list2, true);
        InsertionSort.sort(list3, true);

        assertArrayEquals(new Integer[]{1, 1}, list1.toArray());
        assertArrayEquals(new Integer[]{1, 2}, list2.toArray());
        assertArrayEquals(new Integer[]{1, 2}, list3.toArray());
    }

    @Test
    void sortDescengingTwoElementsList() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(2, 1));

        InsertionSort.sort(list1, false);
        InsertionSort.sort(list2, false);
        InsertionSort.sort(list3, false);

        assertArrayEquals(new Integer[]{1, 1}, list1.toArray());
        assertArrayEquals(new Integer[]{2, 1}, list2.toArray());
        assertArrayEquals(new Integer[]{2, 1}, list3.toArray());
    }

    @Test
    void ascendingSortThreeElementsArray() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 1, 2));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 1));
        ArrayList<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 2));
        ArrayList<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> list6 = new ArrayList<>(Arrays.asList(2, 1, 1));
        ArrayList<Integer> list7 = new ArrayList<>(Arrays.asList(2, 1, 2));
        ArrayList<Integer> list8 = new ArrayList<>(Arrays.asList(2, 2, 1));
        ArrayList<Integer> list9 = new ArrayList<>(Arrays.asList(2, 3, 1));
        ArrayList<Integer> list10 = new ArrayList<>(Arrays.asList(3, 1, 2));
        ArrayList<Integer> list11 = new ArrayList<>(Arrays.asList(3, 2, 1));

        InsertionSort.sort(list1, true);
        InsertionSort.sort(list2, true);
        InsertionSort.sort(list3, true);
        InsertionSort.sort(list4, true);
        InsertionSort.sort(list5, true);
        InsertionSort.sort(list6, true);
        InsertionSort.sort(list7, true);
        InsertionSort.sort(list8, true);
        InsertionSort.sort(list9, true);
        InsertionSort.sort(list10, true);
        InsertionSort.sort(list11, true);

        assertArrayEquals(new Integer[]{1, 1, 1}, list1.toArray());
        assertArrayEquals(new Integer[]{1, 1, 2}, list2.toArray());
        assertArrayEquals(new Integer[]{1, 1, 2}, list3.toArray());
        assertArrayEquals(new Integer[]{1, 2, 2}, list4.toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, list5.toArray());
        assertArrayEquals(new Integer[]{1, 1, 2}, list6.toArray());
        assertArrayEquals(new Integer[]{1, 2, 2}, list7.toArray());
        assertArrayEquals(new Integer[]{1, 2, 2}, list8.toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, list9.toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, list10.toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, list11.toArray());
    }

    @Test
    void descendingSortThreeElementsArray() {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 1));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 1, 2));
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 1));
        ArrayList<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 2));
        ArrayList<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> list6 = new ArrayList<>(Arrays.asList(2, 1, 1));
        ArrayList<Integer> list7 = new ArrayList<>(Arrays.asList(2, 1, 2));
        ArrayList<Integer> list8 = new ArrayList<>(Arrays.asList(2, 2, 1));
        ArrayList<Integer> list9 = new ArrayList<>(Arrays.asList(2, 3, 1));
        ArrayList<Integer> list10 = new ArrayList<>(Arrays.asList(3, 1, 2));
        ArrayList<Integer> list11 = new ArrayList<>(Arrays.asList(3, 2, 1));

        InsertionSort.sort(list1, false);
        InsertionSort.sort(list2, false);
        InsertionSort.sort(list3, false);
        InsertionSort.sort(list4, false);
        InsertionSort.sort(list5, false);
        InsertionSort.sort(list6, false);
        InsertionSort.sort(list7, false);
        InsertionSort.sort(list8, false);
        InsertionSort.sort(list9, false);
        InsertionSort.sort(list10, false);
        InsertionSort.sort(list11, false);

        assertArrayEquals(new Integer[]{1, 1, 1}, list1.toArray());
        assertArrayEquals(new Integer[]{2, 1, 1}, list2.toArray());
        assertArrayEquals(new Integer[]{2, 1, 1}, list3.toArray());
        assertArrayEquals(new Integer[]{2, 2, 1}, list4.toArray());
        assertArrayEquals(new Integer[]{3, 2, 1}, list5.toArray());
        assertArrayEquals(new Integer[]{2, 1, 1}, list6.toArray());
        assertArrayEquals(new Integer[]{2, 2, 1}, list7.toArray());
        assertArrayEquals(new Integer[]{2, 2, 1}, list8.toArray());
        assertArrayEquals(new Integer[]{3, 2, 1}, list9.toArray());
        assertArrayEquals(new Integer[]{3, 2, 1}, list10.toArray());
        assertArrayEquals(new Integer[]{3, 2, 1}, list11.toArray());
    }

    @Test
    void stringSort() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("Alphabet", "Beta", "Zoo", "Zoo", "Field", "Java", "Scala"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("Alphabet", "Beta", "Zoo", "Zoo", "Field", "Java", "Scala"));

        InsertionSort.sort(list1, true);
        InsertionSort.sort(list2, false);

        assertArrayEquals(
                new String[]{"Alphabet",
                        "Beta",
                        "Field",
                        "Java",
                        "Scala",
                        "Zoo",
                        "Zoo"},
                list1.toArray());

        assertArrayEquals(
                new String[]{"Zoo",
                        "Zoo",
                        "Scala",
                        "Java",
                        "Field",
                        "Beta",
                        "Alphabet"},
                list2.toArray());
    }
}