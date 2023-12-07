package ma.yc.marjane.DTO;

import lombok.Data;
import ma.yc.marjane.Models.ProductModel;

@Data
public class PromotionDTO {
    int id;
    float promotionPercentage;
    ProductModel productModel;
    String status;

}
