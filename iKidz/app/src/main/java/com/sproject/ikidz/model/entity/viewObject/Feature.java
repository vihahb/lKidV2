package com.sproject.ikidz.model.entity.viewObject;

public class Feature {

    private String name;
    private int id;

    public Feature() {
    }

    public Feature(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
