package ma.yc.marjane.DTO;

import lombok.*;

@Data

public class LoginReqDTO {
    private String email;
    private String password;
    private String type;
}
