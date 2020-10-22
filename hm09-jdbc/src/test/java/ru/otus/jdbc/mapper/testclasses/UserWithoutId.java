package ru.otus.jdbc.mapper.testclasses;

import ru.otus.core.annotations.Column;
import ru.otus.core.annotations.Id;
import ru.otus.core.annotations.Table;

import java.util.Objects;

@Table
public class UserWithoutId {

    @Column
    private long id;
    @Column
    private String name;
    @Column
    private int age;

    public UserWithoutId() {
    }

    public UserWithoutId(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWithoutId user = (UserWithoutId) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
