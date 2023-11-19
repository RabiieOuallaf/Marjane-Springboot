package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.RayonAdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RayonAdminRepository extends JpaRepository<RayonAdminModel,Integer> {

    Optional<RayonAdminModel> findByEmail(String email);

    void deleteByEmail(String email);
}
