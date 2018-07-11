package com.snq.ikidz.teacher.model.entity.viewObject;

public class Feature {

    int resource;
    boolean showFull;
    int mipmap;
    private String name;
    private int id;
    private int notifyCount;

    public Feature() {
    }

    public Feature(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Feature(String name, int id, int notifyCount) {
        this.name = name;
        this.id = id;
        this.notifyCount = notifyCount;
    }

    public Feature(String name, int id, int notifyCount, int resource, boolean showFull, int mipmap) {
        this.name = name;
        this.id = id;
        this.notifyCount = notifyCount;
        this.resource = resource;
        this.showFull = showFull;
        this.mipmap = mipmap;
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

    public int getNotifyCount() {
        return notifyCount;
    }

    public void setNotifyCount(int notifyCount) {
        this.notifyCount = notifyCount;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public boolean isShowFull() {
        return showFull;
    }

    public void setShowFull(boolean showFull) {
        this.showFull = showFull;
    }

    public int getMipmap() {
        return mipmap;
    }

    public void setMipmap(int mipmap) {
        this.mipmap = mipmap;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", notifyCount=" + notifyCount +
                ", resource=" + resource +
                ", showFull=" + showFull +
                ", mipmap=" + mipmap +
                '}';
    }
}
