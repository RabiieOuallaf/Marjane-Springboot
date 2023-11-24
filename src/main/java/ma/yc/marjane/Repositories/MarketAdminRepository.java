package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.MarketAdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.io.Serial;
import java.util.Optional;

@Repository
public interface MarketAdminRepository extends JpaRepository<MarketAdminModel, Serial> {
    MarketAdminModel findByEmail(String email);
    void deleteByEmail(String email);

}
