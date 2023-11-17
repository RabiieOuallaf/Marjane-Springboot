package ma.yc.marjane.Repositories;

import ma.yc.marjane.Models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serial;

public interface CategoryRepository extends JpaRepository<CategoryModel, Serial> {

}
