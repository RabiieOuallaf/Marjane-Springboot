package ma.yc.marjane.Observer;

import org.springframework.stereotype.Component;

@Component
public class PromotionManager implements PromotionListener{


    @Override
    public void notifyPromotion() {
        System.out.println("Promotion manager received promotion notification.");
    }
}
