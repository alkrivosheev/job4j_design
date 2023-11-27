package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import org.assertj.core.data.Index;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkLIst() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("magna", "ullamcorper", "tincidunt", "Sed", "semper", "velit");
        assertThat(list).hasSize(6)
                .contains("ullamcorper")
                .contains("semper", Index.atIndex(4))
                .containsAnyOf("Sed", "velit", "magna")
                .doesNotContain("tincidunt", Index.atIndex(3));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("weather", "dull", "domed", "divided", "walls", "room12");
        assertThat(set).hasSize(6)
                .contains("dull")
                .containsAnyOf("weather", "divided", "domed");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("look", "tool", "debug", "scada", "cipherLab", "Pityful");
        assertThat(map).hasSize(6)
                .containsKey("debug")
                .containsValues(5)
                .containsEntry("cipherLab", 4);
    }
}