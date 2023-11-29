package ma.yc.marjane.Observer;

import ma.yc.marjane.DTO.PromotionDTO;
import org.springframework.context.ApplicationEvent;

public class PromotionEvent extends ApplicationEvent {
    private final PromotionDTO promotionDTO;

    public PromotionEvent(Object source,PromotionDTO promotionDTO) {
        super(source);
        this.promotionDTO = promotionDTO;
    }
    public PromotionDTO getPromotionDTO() {
        return promotionDTO;
    }
}
