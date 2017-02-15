package com.theironyard.entitiies;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

/**
 * Created by erikjakubowski on 1/30/17.
 */

@Entity
@Table (name = "index")
public class Index {
    @Id
    int id;

    @Column(nullable = false)
    String value;

    public Index() {

    }

    public Index(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
