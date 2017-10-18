package com.lanou.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class Classes {
    private int id;
    private String name;
    private Set<Student> students = new HashSet<>();

    public Classes() {
    }

    public Classes(String name) {
        this.name = name;
    }

    public Classes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Classes(int id, String name, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
