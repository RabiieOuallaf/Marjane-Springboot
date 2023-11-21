package ma.yc.marjane.DTO;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDTO {
    private String email;
    private String token;
}
