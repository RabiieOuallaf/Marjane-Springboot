package ma.yc.marjane.Controllers.Implementation.Promotion;

import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PromotionModel promotion) {
        PromotionDTO promotionDTO = promotionService.create(promotion);

        if(promotionDTO != null) {
            return ResponseEntity.ok("Promotion with percentage of +" + promotionDTO.getPromotionPercentage() + "has been applied");
        }else {
            return ResponseEntity.badRequest().body("Category creation failed, check logs for more details");
        }
    }

//    @GetMapping("/readAll/{rayonAdminId}")
//    public ResponseEntity<String> readAll(@PathVariable int rayonAdminId) {
//        PromotionDTO promotionDTO = promotionService.readAll(rayonAdminId);
//    }
    @PostMapping("/accept")
    public ResponseEntity<String> acceptPromotion(@RequestBody PromotionDTO promotionDTO) {
        try {

            Optional<Optional<ProductDTO>> acceptedPromotion = promotionService.acceptPromotion(promotionDTO);

            if (acceptedPromotion != null) {
                return ResponseEntity.ok("Promotion accepted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promotion not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


}
