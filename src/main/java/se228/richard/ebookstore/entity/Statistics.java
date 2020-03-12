package se228.richard.ebookstore.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Statistics {

    private int id;
    private String name;
    private int number;
    private double total;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Statistics() {
    }

    public Statistics(int id, String name, int number, double total) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.total = total;
    }
}
