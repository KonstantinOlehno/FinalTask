package com.university.selectioncommittee.entity;

public class AbstractEntity {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractUser{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
