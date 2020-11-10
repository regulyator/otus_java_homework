package ru.otus.core.model;

import ru.otus.core.annotations.Column;
import ru.otus.core.annotations.Id;
import ru.otus.core.annotations.Table;

import java.util.Objects;

@Table
public class Account {

    @Id
    @Column(columnName = "no")
    private long no;
    @Column
    private String type;
    @Column
    private double rest;

    public Account() {
    }

    public Account(long no, String type, double rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRest() {
        return rest;
    }

    public void setRest(double rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return no == account.no;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
