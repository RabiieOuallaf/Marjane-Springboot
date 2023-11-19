package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "CategoryModel")
@Table(name = "category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_admin_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "rayon_admin_fkey"))
    RayonAdminModel rayonAdmin;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductModel> products;
}
