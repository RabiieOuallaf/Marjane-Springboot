package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.CategoryDTO;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Mappers.ProductMapper;
import ma.yc.marjane.Mappers.PromotionMapper;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Observer.PromotionManager;
import ma.yc.marjane.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PromotionService {
    @Autowired
    private final PromotionRepository promotionRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    private ApplicationEventPublisher eventPublisher;
    private PromotionManager promotionManager;

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
                PromotionDTO promotionDTO = PromotionMapper.promotionMapper.toDTO(createdPromotionModel);
                promotionManager.notifyPromotion();
                return promotionDTO;
            }
        } else {
            log.error("Product or CategoryModel is null");
        }
        return null;
    }
    /* ****
     *
     * Description : apply Promotion
     * Helpers : findCategory(String name) method check if market category with given email already exists
     * @param : PromotionDTO(Model) as a parameter
     *
     **** */

    public ProductDTO acceptPromotion(int promotionId) {
        PromotionModel promotionModel = readById(promotionId);
        ProductModel productDTO = promotionModel.getProduct();
        ProductDTO productModelToUpdate = productService.read(productDTO.getId());

        if (productModelToUpdate != null) {

            double originalPrice = productModelToUpdate.getPrice();
            double promotionPercentage = promotionModel.getPromotionPercentage();

            double discountedPrice = originalPrice - (originalPrice * promotionPercentage / 100);
            productModelToUpdate.setPrice(discountedPrice);
            System.out.println(productModelToUpdate);

            ProductModel updatedProductDTO = ProductMapper.productMapper.toEntity(productModelToUpdate);
            updatedProductDTO.setCategory(productModelToUpdate.getCategoryModel());
            promotionModel.setStatus("accepted");
             promotionRepository.save(promotionModel);
            ProductDTO updatedProduct = productService.update(updatedProductDTO);
            if(updatedProduct != null) {
                return updatedProduct;
            }else {
                return null;
            }

        } else {
            log.error("Product not found for the given promotion");
            return null;
        }
    }

    /*
    *  Description : read all promotions within a category
    * @param categoryId
     */

    public List<PromotionModel> readByCategory(int categoryId) {
        CategoryDTO category = categoryService.read(categoryId);
        List<PromotionModel> promotionModelList = readAll();
        List<ProductModel> productModelList = category.getProducts();
        List<PromotionModel> matchingPromotion = new ArrayList<>();

        for(ProductModel productModel : productModelList) {
            for(PromotionModel promotionModel : promotionModelList) {
                if(promotionModel.getProduct() != null && productModel.getId() != 0) {
                    if(promotionModel.getProduct().getId() == productModel.getId()) {
                        promotionModel.getProduct().setPromotion(null);
                        promotionModel.getProduct().setCategory(null);
                        matchingPromotion.add(promotionModel);
                    }
                }
            }
        }
        return matchingPromotion;
    }

    public List<PromotionModel> readByStatus(String status) {
        return readAll().stream()
                .filter(promotionModel -> promotionModel.getProduct() != null && promotionModel.getStatus().equals(status))
                .peek(promotionModel -> {
                    promotionModel.getProduct().setPromotion(null);
                    promotionModel.getProduct().setCategory(null);
                })
                .collect(Collectors.toList());
    }

    public void rejectPromotion(int promotionId) {
        PromotionModel promotionModel = promotionRepository.findById(promotionId).orElse(null);

        if (promotionModel != null && "ON_HOLD".equals(promotionModel.getStatus())) {
            promotionModel.setStatus("rejected");
            promotionRepository.save(promotionModel);

        } else {
            log.error("Promotion not found or already accepted/rejected for the given ID: " + promotionId);
        }
    }

    /*
     *  Description : read a promotions by it's id
     * @param promotionId
     */
    public PromotionModel readById(int promotionId) {
        Optional<PromotionModel> promotionModel = promotionRepository.findById(promotionId);
        if(promotionModel.isPresent()) {
            return promotionModel.get();
        }else {
            return null;
        }
    }

    /*
     *  Description : read all promotions
     * @param promotionId
     */
    public List<PromotionModel> readAll() {
        List<PromotionModel> promotionModelList =  promotionRepository.findAll();
        return promotionModelList;
    }

    /*
    * Description : Delete a promotion
    * @param promotionId
     */
    public void delete(int promotionId) {
        promotionRepository.deleteById(promotionId);
    }

}
