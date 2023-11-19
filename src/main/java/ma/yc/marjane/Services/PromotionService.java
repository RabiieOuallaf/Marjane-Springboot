package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Mappers.PromotionMapper;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println(productDTO.getCategoryModel().getName() + "<============================================================================================= promotion model");

        if (productDTO != null && productDTO.getCategoryModel() != null) {

            if (promotionModel.getPromotionPercentage() > 50 && productDTO.getQuantity() > 20) {
                log.error("Sorry, You can't apply a more than 50% promotion to a product with quantity of" + productDTO.getQuantity());
            } else if (promotionModel.getPromotionPercentage() <= 15 && "multimedia".equals(productDTO.getCategoryModel().getName())) {
                PromotionModel createdPromotionModel = promotionRepository.save(promotionModel);
                PromotionDTO promotionDTO = PromotionMapper.promotionMapper.toDTO(createdPromotionModel);
                return promotionDTO;
            } else if (
                    promotionModel.getPromotionPercentage() > 50
                            && promotionModel.getPromotionPercentage() <= 70
                            && productDTO.getQuantity() < 20
                            && !("multimedia".equals(productDTO.getCategoryModel().getName()))
            ) {
                PromotionModel createdPromotionModel = promotionRepository.save(promotionModel);
                PromotionDTO promotionDTO = PromotionMapper.promotionMapper.toDTO(createdPromotionModel);
                return promotionDTO;
            }
        } else {
            log.error("Product or CategoryModel is null");
        }
        return null;
    }

}
