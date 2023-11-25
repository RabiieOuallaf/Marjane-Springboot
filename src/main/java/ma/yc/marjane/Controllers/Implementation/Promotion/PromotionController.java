package ma.yc.marjane.Controllers.Implementation.Promotion;

import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion")

public class PromotionController {
    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    /* ****
     *
     * POST /api/v1/promotion/create
     * Definition : this method creates a new promotion
     * @Param : PromotionModel
     *
     **** */
    @PreAuthorize("hasAuthority('CREATE_PROMOTION')")

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PromotionModel promotion) {
        PromotionDTO promotionDTO = promotionService.create(promotion);

        if(promotionDTO != null) {
            return ResponseEntity.ok("Promotion with percentage of +" + promotionDTO.getPromotionPercentage() + "has been applied");
        }else {
            return ResponseEntity.badRequest().body("Category creation failed, check logs for more details");
        }
    }
    /* ****
     *
     * POST /api/v1/promotion/accept/{param}
     * Definition : this method creates a new promotion
     * @Param : promotion id
     *
     **** */

//    @PreAuthorize("hasAuthority('ACCEPT_PROMOTION')")
    @PostMapping("/accept/{promotionId}")
    public ResponseEntity<String> acceptPromotion(@PathVariable int promotionId) {
        try {

            ProductDTO acceptedPromotion = promotionService.acceptPromotion(promotionId);

            if (acceptedPromotion != null) {
                return ResponseEntity.ok("Promotion accepted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    /* ****
     *
     * POST /api/v1/promotion/read/{param}
     * Definition : this method reads and returns a promotion by category id
     * @Param : category id
     *
     **** */
    @GetMapping("/read/{categoryId}")
    public ResponseEntity<?> read(@PathVariable int categoryId){
        if(categoryId != 0) {

            List<PromotionModel> promotionModelList = promotionService.readByCategory(categoryId);
            if(!promotionModelList.isEmpty()){
                return ResponseEntity.ok(promotionModelList);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No promotions found");
            }

        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There's an issue with the request parameters please check and try again");
        }
    }


    @PostMapping("/reject/{promotionId}")
    public ResponseEntity<?> reject(@PathVariable int promotionId) {
        if(promotionId != 0) {
            promotionService.delete(promotionId);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("promotion id isn't valid, please check and try again!");
        }
        return null;
    }


}
