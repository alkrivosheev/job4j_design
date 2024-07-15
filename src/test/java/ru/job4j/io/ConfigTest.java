package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndWhiteSpace() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("connection.driver_class")).isEqualTo("org.postgresql.Driver");
    }

    @Test
    void whenNotKey() {
        String path = "./data/appErr.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("A key not found");
    }

    @Test
    void whenNotValue() {
        String path = "./data/appErrVal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("A value not found");
    }

    @Test
    void whenNotKeyAndValue() {
        String path = "./data/appErrKeyVal.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("A key and value not found");
    }

    @Test
    void whenNotEqual() {
        String path = "./data/appErrEqual.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("A = not found ");
    }

    @Test
    void whenkeyValVal() {
        String path = "./data/appKeyValVal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=3");
    }

    @Test
    void whenkeyValEqual() {
        String path = "./data/appKeyValEqual.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=");
    }

}