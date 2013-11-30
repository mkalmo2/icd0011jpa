package model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "SEQ1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    private String street;

    
    
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
    
}
