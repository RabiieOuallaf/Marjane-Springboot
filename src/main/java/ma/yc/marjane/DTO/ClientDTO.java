package ma.yc.marjane.DTO;

import lombok.Data;

@Data
public class ClientDTO {
    private int Id;
    private String fullName;
    private String email;
    private String password;
}
