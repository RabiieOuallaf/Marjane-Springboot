package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "RayonAdminModel")
@Table(name = "rayon_adminstrator")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RayonAdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "fullname")
    private String fullname;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "rayonAdmin")
    private CategoryModel category;

    public String toString() {
        return "RayonAdministratorModel{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", category=" + category +
                ", role='" + role + '\''+
                '}';
    }
}
