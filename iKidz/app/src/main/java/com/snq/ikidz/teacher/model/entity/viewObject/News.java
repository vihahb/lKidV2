package com.snq.ikidz.teacher.model.entity.viewObject;

public class News {
    private String name;
    private String content;

    public News() {
    }

    public News(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
