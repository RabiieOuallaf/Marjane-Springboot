package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MarketAdminModel")
@Table(name = "market_adminstrator")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketAdminModel {
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

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "market_id", referencedColumnName = "id" , foreignKey = @ForeignKey(name = "market_adminstrator_market_id_fkey"))
    private MarjaneMarketModel market;

    public String toString() {
        return "MarketAdminModel{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", market=" + market +
                ", role='" + role + '\''+
                '}';
    }
}

