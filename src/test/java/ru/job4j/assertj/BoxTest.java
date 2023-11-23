package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isNotEmpty();
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 5);
        String name = box.whatsThis();
        assertThat(name).isNotEqualTo("Sphere")
                .isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    void theNumberOfVerticesIs0() {
        Box box = new Box(0, 5);
        int number = box.getNumberOfVertices();
        assertThat(number).isNotEqualTo(5)
                .isEqualTo(0);
    }

    @Test
    void theNumberOfVerticesIsMinus1() {
        Box box = new Box(0, -5);
        int number = box.getNumberOfVertices();
        assertThat(number).isNotEqualTo(0)
                .isEqualTo(-1);
    }

    @Test
    void theBoxIsExist() {
        Box box = new Box(0, 5);
        assertThat(box.isExist()).isTrue()
                .isNotEqualTo(false);
    }

    @Test
    void theBoxIsNotExist() {
        Box box = new Box(7, 0);
        assertThat(box.isExist()).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void theAreaIsEqual0() {
        Box box = new Box(1, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(0)
                .isNotEqualTo(45.6);
    }

    @Test
    void theAreaIsEqual43() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isNotEqualTo(0)
                .isCloseTo(43.30, offset(0.01D));
    }
}
