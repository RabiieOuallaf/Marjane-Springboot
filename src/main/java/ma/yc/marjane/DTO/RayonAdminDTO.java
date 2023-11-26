package ma.yc.marjane.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.marjane.Models.RayonAdminModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RayonAdminDTO {
    int id;
    String email;
    String fullname;
    String password;
    String role;

    RayonAdminModel rayonAdminModel;
}
