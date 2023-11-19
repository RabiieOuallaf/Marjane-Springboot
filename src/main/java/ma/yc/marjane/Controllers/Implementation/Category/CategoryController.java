package ma.yc.marjane.Controllers.Implementation.Category;

import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.CategoryDTO;
import ma.yc.marjane.Mappers.CategoryMapper;
import ma.yc.marjane.Models.CategoryModel;
import ma.yc.marjane.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService   ) {
        this.categoryService = categoryService;
    }

    /* ****
    *
    * POST /api/v1/category/create
    * Definition : this method creates a new category
    * @Param : CategoryModel
    *
      **** */

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CategoryModel category){
        CategoryDTO categoryDTO = categoryService.create(category);
        if(categoryDTO != null){
            return ResponseEntity.ok("Category with name " + category.getName() +" And id " + category.getId() + "Created successfully");
        }else {
            return ResponseEntity.badRequest().body("Category creation failed, check logs for more details");
        }
    }

    /* ****
     * PUT /api/v1/category/update
     * Request body : CategoryModel
     * Description : update an existing categoy
     *
     * ****/
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody CategoryModel category){
        Optional<CategoryModel> updatedCategoryModel = categoryService.update(category);

        if(updatedCategoryModel.isPresent()) {
            return ResponseEntity.ok("Category with name " + category.getName() +" And id " + category.getId() + "Updated successfully");
        }else {
            return ResponseEntity.badRequest().body("Category updating failed, check logs for more details");
        }
    }
    /* ****
     * POST /api/v1/category/readAll
     * Request body : none
     * Description : returns a list of categories
     *
     * ****/
    @GetMapping("/readAll")
    public ResponseEntity<List<CategoryDTO>> readAll() {
        try {
            List<CategoryDTO> categories = categoryService.readAll();

            return ResponseEntity.ok().body(categories);

        } catch (Exception e) {
            // Log the exception for troubleshooting
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /* ****
     * GET /api/v1/category/read/{param}
     * @param : Integer id
     * Description : fetchs a category
     *
     * ****/
    @GetMapping("/read/{id}")
    public ResponseEntity<String> read(@PathVariable Integer id) {
        CategoryDTO categoryDTO = categoryService.read(id);

        if(categoryDTO != null){
            return ResponseEntity.ok().body("Category : " + categoryDTO);
        }else {
            return ResponseEntity.badRequest().body("Category not found");
        }

    }


    /* ****
     * DELETE /api/v1/category/delete/{param}
     * @param : Integer id
     * Description : fitches a category
     *
     * ****/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
