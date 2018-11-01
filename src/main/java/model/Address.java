package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "aadress")
public class Address {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "jarjend", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @Column(name = "tanav")
    private String street;

    public Address(String street) {
        this.street = street;
    }
}
