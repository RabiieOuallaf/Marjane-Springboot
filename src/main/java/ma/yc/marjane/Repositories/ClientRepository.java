package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    ClientModel findByEmail(String email);

    void deleteByEmail(String email);
}
