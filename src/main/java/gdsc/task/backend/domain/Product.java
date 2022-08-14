package gdsc.task.backend.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Table(name="Product")
@Entity
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "id_seq", schema = "public")
    @Column(name = "product_id", nullable = false, unique = true)
    private Long id;
    @Column(name = "product_name", nullable = false, unique = false)
    private String name;
    @Column(name = "product_price", nullable = false, unique = false)
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }
}
