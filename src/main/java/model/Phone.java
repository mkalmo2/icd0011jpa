package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class Phone {

    private Long id;

    private String number;

    public Phone(String number) {
        this.number = number;
    }

}
