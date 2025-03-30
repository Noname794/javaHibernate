package model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="manager")
public class manager implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_manager;
	private String name;
	private String country;
	
	@ManyToMany(mappedBy = "manager")
	private Set<product> products;
	public Set<product> getProducts() {
		return products;
	}
	public void setProducts(Set<product> products) {
		this.products = products;
	}
	public manager() {

	}
	public manager(int id_manager, String name, String country) {
		super();
		this.id_manager = id_manager;
		this.name = name;
		this.country = country;
	}
	public int getId_manager() {
		return id_manager;
	}
	public void setId_manager(int id_manager) {
		this.id_manager = id_manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
