package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.CashierAgentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierAgentRepository extends JpaRepository<CashierAgentModel,Integer> {
    CashierAgentModel findByEmail(String email);
}
