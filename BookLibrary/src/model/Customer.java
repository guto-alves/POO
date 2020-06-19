package model;

import java.util.List;

public class Customer {
	private String rg;
	private String cpf;
	private String name;
	private String email;
	private List<String> phones;
	private Address address;

	public Customer(String rg, String cpf, String name, String email) {
		this(rg, cpf, name, email, null, null);
	}

	public Customer(String rg, String cpf, String name, String email, 
			List<String> phones, Address address) {
		this.rg = rg;
		this.cpf = cpf;
		this.name = name;
		this.email = email;
		this.phones = phones;
		this.address = address;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
