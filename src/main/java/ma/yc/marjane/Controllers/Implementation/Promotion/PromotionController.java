package ma.yc.marjane.Controllers.Implementation.Promotion;

import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Models.PromotionModel;
import ma.yc.marjane.Services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
