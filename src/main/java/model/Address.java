package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "aadress")
public class Address extends BaseEntity {

    @Column(name = "tanav")
    private String street;

    public Address(String street) {
        this.street = street;
    }
}
