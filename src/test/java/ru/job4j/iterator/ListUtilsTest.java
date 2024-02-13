package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeIf(input, n -> (n.equals(1)));
        assertThat(input).hasSize(2).containsSequence(2, 3);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        List<Integer> output = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.removeAll(input, output);
        assertThat(input).hasSize(3).containsSequence(3, 4, 5);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.replaceIf(input, n -> (n.equals(3)), 6);
        assertThat(input).hasSize(5).containsSequence(1, 2, 6, 4, 5);
    }
}