package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.GeneralAdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralAdminRepository extends JpaRepository<GeneralAdminModel, Integer> {
    GeneralAdminModel readByEmail(String email);
}
