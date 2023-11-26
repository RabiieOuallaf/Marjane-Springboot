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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_admin_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "rayon_admin_fkey"))
    RayonAdminModel rayonAdmin;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<ProductModel> products;
    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rayonAdmin=" + (rayonAdmin != null ? rayonAdmin.getId() : "null") +
                '}';
    }

}
