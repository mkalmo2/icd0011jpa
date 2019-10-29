package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private Long id;

    private String street;

    public Address(String street) {
        this.street = street;
    }
}
