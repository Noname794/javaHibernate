package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "product") // Đảm bảo trùng với tên bảng trong DB
public class product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date dateProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    private category ct;

    @ManyToMany
    @JoinTable(
        name = "pro_man",
        joinColumns = {@JoinColumn(name="product_id")},
        inverseJoinColumns = {@JoinColumn(name="manager_id")}
    )
    private Set<manager> manager;

    // Thêm thuộc tính mới
    private int price;

    public product(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public product() {}

    public product(int id, String name, Date dateProduct, category ct, int price) {
        this.id = id;
        this.name = name;
        this.dateProduct = dateProduct;
        this.ct = ct;
        this.price = price;
    }

    // Getter và Setter mới
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    // Getter & Setter cũ
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDateProduct() { return dateProduct; }
    public void setDateProduct(Date dateProduct) { this.dateProduct = dateProduct; }

    public category getCt() { return ct; }
    public void setCt(category ct) { this.ct = ct; }

    public Set<manager> getManager() { return manager; }
    public void setManager(Set<manager> manager) { this.manager = manager; }
}