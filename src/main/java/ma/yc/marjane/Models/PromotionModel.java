package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PromotionModel")
@Table(name = "promotion")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "promotion_percentage")
    private float promotionPercentage;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private ProductModel product;
}
