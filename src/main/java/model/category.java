package model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "category") // Đảm bảo Hibernate biết tên bảng
public class category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_category;

    private String name;
    
    @OneToMany(mappedBy = "ct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<product> products;
    
    
    public List<product> getProducts() {
		return products;
	}

	public void setProducts(List<product> products) {
		this.products = products;
	}

	public category() {}

    public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public category(int id, String name) {
        this.id_category = id;
        this.name = name;
    }



    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
