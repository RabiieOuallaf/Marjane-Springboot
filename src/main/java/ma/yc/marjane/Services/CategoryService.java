package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.Models.CategoryModel;
import ma.yc.marjane.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    /* ****
     *
     * Description : create a new Category
     * Helpers : findCategory(String name) method check if market category with given email already exists
     * @param : CategoryEntity(Model) as a parameter
     *
     **** */
    @Transactional
    public Optional<CategoryModel> create(CategoryModel categoryModel) {
        var existingCategory = findCategory(categoryModel.getName());

        if(existingCategory.isPresent()){
            log.error("Category id {} already exists", categoryModel.getId());
        }

        CategoryModel createdCategoryModel = categoryRepository.save(categoryModel);
        return Optional.of(createdCategoryModel);
    }

    private Optional<CategoryModel> findCategory(String name) {
        Optional<CategoryModel> categoryModel = categoryRepository.findByName(name);
        return categoryModel;

    }
    private Optional<CategoryModel> findCategory(Integer id) {
        return categoryRepository.findById(id);

    }
    /* ****
     *
     * Description : update a Category
     * Helpers : findCategory(Integer id) method check if market category with given email already exists
     * @param : CategoryEntity(Model) as a parameter
     *
     **** */
    public  Optional<CategoryModel> update(CategoryModel category) {
        var existedCategory = findCategory(category.getId());

        if(existedCategory.isEmpty()) {
            log.error("Category id " + existedCategory.get().getId() + "doesn't exist'");
            return Optional.empty();
        }

        if(category.getId() == 0){
            log.error("Cannot delete Market admin without a valid id");
        }
        CategoryModel categoryModel = categoryRepository.save(category);
        return Optional.of(categoryModel);
    }

    /* ****
     *
     * Description : get a user by id
     * Helpers : findCategory(Integer id) method check if market category with given email already exists
     * @param : category id
     *
     **** */

    public ResponseEntity<Optional<CategoryModel>> read(Integer id) {
        Optional<CategoryModel> categoryModel = findCategory(id);
        if(categoryModel.isPresent()) {
            return ResponseEntity.ok().body(categoryModel);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    /* ****
    *
    * Description : fetches all categories
    * Helpers : findCategory(Integer id) method check if market category with given email already exists
    *
      **** */

    public List<CategoryModel> readAll() {
        List<CategoryModel> categories = categoryRepository.findAll();
        return categories;
    }

    /* ****
     *
     * Description : delete category (Register it)
     * Helpers : findCategory(Integer id) method check if market category with given email already exists
     * @param : id
     *
     **** */

    public ResponseEntity<Void> delete(Integer id) {
        var existingCategory = findCategory(id);

        if(existingCategory.isEmpty()) {
            log.error("Category with id " + id+ " doesn't exist'");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(existingCategory.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
