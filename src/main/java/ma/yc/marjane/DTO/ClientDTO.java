package ma.yc.marjane.DTO;

import lombok.Data;

@Data
public class ClientDTO {
    private int Id;
    private String fullname;
    private String email;
    private String password;
}
