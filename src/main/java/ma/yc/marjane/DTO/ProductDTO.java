package ma.yc.marjane.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.yc.marjane.Models.CategoryModel;
import ma.yc.marjane.Models.PromotionModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private CategoryModel categoryModel;
    private int quantity;
    private PromotionModel promotionModel;
}
