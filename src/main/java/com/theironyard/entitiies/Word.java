package com.theironyard.entitiies;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by erikjakubowski on 1/30/17.
 */
@Entity
@Table (name = "wordList")
public class Word {

    @Id
    int id;

    @Column
    String word;

    @Column
    String locations;


    public Word() {

    }


    public Word(int id,String word, String locations) {
        this.word =word;
        this.id=id;
        this.locations=locations;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Word(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
