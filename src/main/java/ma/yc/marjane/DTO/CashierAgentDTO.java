package ma.yc.marjane.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CashierAgentDTO {
    int id;
    String email;
    String fullname;
    String password;
    String role;
}
