package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleNameIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Student");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Student");
    }

    @Test
    void whenReplaceThenRoleNameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.replace("1", new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.replace("10", new Role("10", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Student");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsStudent() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Student");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        boolean result = store.replace("1", new Role("1", "Admin"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Student"));
        boolean result = store.replace("10", new Role("10", "Admin"));
        assertThat(result).isFalse();
    }
}