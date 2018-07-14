package com.snq.ikidz.teacher.model.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PhoneBookData {
    @Expose
    private ClassEntity class_info;
    @Expose
    private List<ClassPeopleEntity> teachers;
    @Expose
    private List<ClassPeopleEntity> parents;

    public ClassEntity getClass_info() {
        return class_info;
    }

    public void setClass_info(ClassEntity class_info) {
        this.class_info = class_info;
    }

    public List<ClassPeopleEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<ClassPeopleEntity> teachers) {
        this.teachers = teachers;
    }

    public List<ClassPeopleEntity> getParents() {
        return parents;
    }

    public void setParents(List<ClassPeopleEntity> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "PhoneBookData{" +
                "class_info=" + class_info +
                ", teachers=" + teachers +
                ", parents=" + parents +
                '}';
    }
}
