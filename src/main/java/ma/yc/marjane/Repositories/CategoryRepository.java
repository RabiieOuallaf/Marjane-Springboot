package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    Optional<CategoryModel> findByName(String name);

    Optional<CategoryModel> findById(Integer id);
    void deleteById(Integer id);
}
