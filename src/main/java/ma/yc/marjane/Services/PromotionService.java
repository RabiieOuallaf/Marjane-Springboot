package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Mappers.ProductMapper;
import ma.yc.marjane.Mappers.PromotionMapper;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Models.RayonAdminModel;
import ma.yc.marjane.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PromotionService {
    @Autowired
    private final PromotionRepository promotionRepository;
    private final ProductService productService;

    /* ****
     *
     * Description : create a new Promotion
     * Helpers : findCategory(String name) method check if market category with given email already exists
     * @param : PromotionEntity(Model) as a parameter
     *
     **** */
    @Transactional
    public PromotionDTO create(PromotionModel promotionModel) {
        ProductDTO productDTO = productService.read(promotionModel.getProduct().getId());

        if (productDTO != null && productDTO.getCategoryModel() != null) {

            if (promotionModel.getPromotionPercentage() > 50 && productDTO.getQuantity() > 20) {
                log.error("Sorry, You can't apply a more than 50% promotion to a product with quantity of" + productDTO.getQuantity());
            } else if (promotionModel.getPromotionPercentage() <= 15 && "multimedia".equals(productDTO.getCategoryModel().getName())) {
                PromotionModel createdPromotionModel = promotionRepository.save(promotionModel);
                PromotionDTO promotionDTO = PromotionMapper.promotionMapper.toDTO(createdPromotionModel);
                return promotionDTO;
            }else if(promotionModel.getPromotionPercentage() > 15 && "multimedia".equals(productDTO.getCategoryModel().getName())){
                log.error("Sorry, you can't apply a more than 15% promotion to multimedia products");
            } else if (
                    promotionModel.getPromotionPercentage() > 50
                            && promotionModel.getPromotionPercentage() <= 70
                            && productDTO.getQuantity() < 20
                            && !("multimedia".equals(productDTO.getCategoryModel().getName()))
            ) {
                PromotionModel createdPromotionModel = promotionRepository.save(promotionModel);
                System.out.println("Promotion DTO" + createdPromotionModel);
                PromotionDTO promotionDTO = PromotionMapper.promotionMapper.toDTO(createdPromotionModel);
                return promotionDTO;
            }
        } else {
            log.error("Product or CategoryModel is null");
        }
        return null;
    }


    public Optional<Optional<ProductDTO>> acceptPromotion(PromotionDTO promotionDTO) {
        ProductModel productDTO = promotionDTO.getProductModel();
        ProductDTO productModelToUpdate = productService.read(productDTO.getId());

        if (productModelToUpdate != null) {
            double originalPrice = productModelToUpdate.getPrice();
            double promotionPercentage = promotionDTO.getPromotionPercentage();

            double discountedPrice = originalPrice - (originalPrice * promotionPercentage / 100);

            productModelToUpdate.setPrice(discountedPrice);

            ProductModel updatedProductDTO = ProductMapper.productMapper.toEntity(productModelToUpdate);

            Optional<ProductDTO> updatedProduct = productService.update(updatedProductDTO);

            return Optional.of(updatedProduct);
        } else {
            log.error("Product not found for the given promotion");
            return null;
        }
    }


//    public PromotionDTO readAll(Integer rayonAdminId) {
//    }
}
