package ma.yc.marjane.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.marjane.Models.CategoryModel;
import ma.yc.marjane.Models.ProductModel;

@Data
public class PromotionDTO {
    int id;
    float promotionPercentage;
    ProductModel productModel;


}
