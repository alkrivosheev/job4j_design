package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNameNotContainsEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"w=r", "fmx", "fkk=", "cdd=", "dlkv="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("fmx does not contain the symbol '='");
    }

    @Test
    void checkNameNotStartEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Daniil=Xxots", "=effm", "aksel-hxgn", "sec=ndus", "Evgen=777"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=effm does not contain a key");
    }

    @Test
    void checkNameNotEndEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"System=Shock", "exe=win", "elf=run", "sec=", "start=end"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("sec= does not contain a value");
    }

    @Test
    void checkLengthIsZero() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Names array is empty");
    }
}