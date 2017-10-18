package com.lanou.domain;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class Student {
    private int id;
    private String name;
    private Classes classes;

    public Student() {
    }

    public Student(int id, String name, Classes classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
