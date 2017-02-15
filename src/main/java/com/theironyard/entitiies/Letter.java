package com.theironyard.entitiies;

import javax.persistence.*;

/**
 * Created by erikjakubowski on 2/8/17.
 */
@Entity
@Table(name = "letterList")
public class Letter {

    @Id
    int id;

    @Column
    String letter;

    @Column
    String locations;

    public Letter() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Letter(int id,String letter, String locations) {
        this.id = id;
        this.letter = letter;
        this.locations=locations;
    }
}
