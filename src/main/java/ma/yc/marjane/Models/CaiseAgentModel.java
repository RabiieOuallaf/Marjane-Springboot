package ma.yc.marjane.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "CaiseAgentModel")
@Table(name = "caise_gent")
@Data
public class CaiseAgentModel {
    @Id
    private Long id;


}
