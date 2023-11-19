package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionModel, Integer> {
}
