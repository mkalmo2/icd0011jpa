package model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "isik")
public class Person {

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "jarjend", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @Column(name = "nimi")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aadressi_id")
    private Address address;

    @ElementCollection
    @CollectionTable(
            name = "isiku_telefonid",
            joinColumns=@JoinColumn(name = "isiku_id", referencedColumnName = "id")
    )
    private List<Phone> phones = new ArrayList<>();



}
