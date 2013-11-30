package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "SEQ1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person", cascade = { CascadeType.ALL })
    private List<Phone> phones;
    
    @OneToOne(cascade = { CascadeType.ALL })
    private Address address;    
    
	@Override
	public String toString() {
		return "Person [id=" + getId() + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public void addPhone(Phone p) {
		if (phones != null) phones = new ArrayList<Phone>();
		phones.add(p);
		p.setPerson(this);
	}
	
}
