package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MarjaneMarketModel")
@Table(name = "marjane_markets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarjaneMarketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    String name;


    @OneToOne(mappedBy = "market" , cascade = CascadeType.ALL)
    private MarketAdminModel marketAdminModel;

    public String toString() {
        return "MarjaneMarketModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
