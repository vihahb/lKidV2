package com.sproject.ikidz.model.entity;

public class ItemDrawer {
    public int id;
    public String name;
    public int resource;

    public ItemDrawer(int id, String name, int resource) {
        this.id = id;
        this.name = name;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ItemDrawer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resource=" + resource +
                '}';
    }
}
