package ma.yc.marjane.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StatisticsService {
    @Autowired
    private final PromotionService promotionService;
    private final String ACCEPTED = "accepted";
    private final String REJECTED = "rejected";
    private final String ON_HOLD = "ON_HOLD";


    public float acceptedPromotionPercentage() {
        int totalPromotions = totalPromotions();
        if (totalPromotions != 0) {
            return (float) promotionService.readByStatus(ACCEPTED).size() / totalPromotions * 100;
        } else {
            return 0.0f;
        }
    }


    public float rejectedPromotionPercentage() {
        int totalPromotions = totalPromotions();
        if (totalPromotions != 0) {
            return (float) promotionService.readByStatus(REJECTED).size() / totalPromotions * 100;
        } else {
            return 0.0f;
        }
    }

    public float onHoldPromotionPercentage() {
        int totalPromotions = totalPromotions();
        if (totalPromotions != 0) {
            return (float) promotionService.readByStatus(ON_HOLD).size() / totalPromotions * 100;
        } else {
            return 0.0f;
        }
    }

    public int totalPromotions() {
        return promotionService.readAll().size();
    }
}
