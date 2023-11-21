package ma.yc.marjane.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.marjane.Models.MarjaneMarketModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketAdminDTO {
    int id;
    String email;
    String fullname;
    String password;
    private MarjaneMarketModel marketModel;
}
