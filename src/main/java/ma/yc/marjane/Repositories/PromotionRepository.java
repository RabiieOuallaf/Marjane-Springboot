package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionModel, Integer> {
}
