package ma.yc.marjane.Controllers.Implementation.Promotion;

import lombok.RequiredArgsConstructor;
import ma.yc.marjane.Services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    @Autowired
    private final StatisticsService statisticsService;

    @GetMapping("/acceptedPromotionPercentage")
    public ResponseEntity<Float> getAcceptedPromotionPercentage() {
        float percentage = statisticsService.acceptedPromotionPercentage();
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/rejectedPromotionPercentage")
    public ResponseEntity<Float> getRejectedPromotionPercentage() {
        float percentage = statisticsService.rejectedPromotionPercentage();
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/onHoldPromotionPercentage")
    public ResponseEntity<Float> getOnHoldPromotionPercentage() {
        float percentage = statisticsService.onHoldPromotionPercentage();
        return ResponseEntity.ok(percentage);
    }

    @GetMapping("/totalPromotions")
    public ResponseEntity<Integer> getTotalPromotions() {
        int totalPromotions = statisticsService.totalPromotions();
        return ResponseEntity.ok(totalPromotions);
    }
}
