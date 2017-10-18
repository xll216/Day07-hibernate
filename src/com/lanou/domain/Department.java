package com.lanou.domain;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class Department {
    private int id;
    private String name;
    private Manager manager;

    public Department() {
    }

    public Department(int id, String name, Manager manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    public Department(String name) {
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
