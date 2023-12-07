package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Optional<ProductModel> findById(Integer id);
    void deleteById(Integer id);

}
