package ma.yc.marjane.DTO;

import lombok.*;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Models.RayonAdminModel;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    int id;
    String name;
    RayonAdminModel rayonAdmin;
    List<ProductModel> products;
}
