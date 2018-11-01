package model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Person {

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    private Long id;

    private String name;

}
