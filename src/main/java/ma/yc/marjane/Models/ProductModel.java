package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ProductModel")
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")

    private String name;

    @Basic
    @Column(name = "price")

    private double price;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private PromotionModel promotion;

    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", promotion=" + (promotion != null ? promotion : "null") +
                ", quantity=" + quantity +
                ", category=" + (category != null ? category : "null") +
                '}';
    }

}
