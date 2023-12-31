package ma.yc.marjane.Controllers.Implementation.Product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    @Autowired

    private final ProductService productService;


    /* ****
     *
     * POST /api/v1/product/create
     * Definition :  creates a new product
     * @Param : ProductModel
     *
     **** */

    @PreAuthorize("hasAuthority('CREATE_PRODUCT')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProductModel productModel) {
        ProductDTO productDTO = productService.create(productModel);
        if(productDTO != null) {
            return ResponseEntity.ok("Product " + productDTO + "Created successfully");
        }else {
            return ResponseEntity.badRequest().body("Product creation failed, check logs for more details");
        }

    }

    /* ****
     *
     * PUT /api/v1/product/update
     * Request body : ProductModel
     * Description : update an existing product
     *
     * ****/

    @PreAuthorize("hasAuthority('UPDATE_PRODUCT')")
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody ProductModel productModel){
        ProductDTO updatedProduct = productService.update(productModel);

        if(updatedProduct != null) {
            return ResponseEntity.ok("Category with name " + productModel.getName() +" And id " + productModel.getId() + "Updated successfully");
        }else {
            return ResponseEntity.badRequest().body("Category updating failed, check logs for more details");
        }
    }

    /* ****
     *
     * GET /api/v1/product/get
     * Request body : product id
     * Description : returns an existing product
     *
     * ****/
    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    @GetMapping("/read/{id}")
    public ResponseEntity<String> read(@PathVariable int id) {
        ProductDTO productDTO = productService.read(id);

        if(productDTO != null){
            return ResponseEntity.ok().body("Product : " +productDTO);
        }else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }
    /* ****
     *
     * POST /api/v1/category/readAll
     * Request body : none
     * Description : returns a list of categories
     *
     * ****/
    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    @GetMapping("/readAll")
    public ResponseEntity<List<ProductDTO>> readAll() {
        try {
            List<ProductDTO> products = productService.readAll();

            return ResponseEntity.ok().body(products);

        } catch (Exception e) {
            // Log the exception for troubleshooting
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* ****
     * DELETE /api/v1/category/delete/{param}
     * @param : Integer id
     * Description : delete a product
     *
     * ****/
    @PreAuthorize("hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
