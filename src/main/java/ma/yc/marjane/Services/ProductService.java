package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.Mappers.ProductMapper;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    /* ****
     *
     * Description : create a new product
     * Helpers : findProduct(Integer id) method check if  already exists
     * @param : ProductEntity(Model) as a parameter
     *
     **** */

    @Transactional
    public ProductDTO create(ProductModel productModel) {
        var existingProduct = findProduct(productModel.getId());

        if(existingProduct.isPresent()){
            log.error("Product with id {} already exists ", productModel.getId());
        }

        ProductModel createdProductModel = productRepository.save(productModel);
        ProductDTO productDTO = ProductMapper.productMapper.toDTO(createdProductModel);

        return productDTO;
    }

    private Optional<ProductModel> findProduct(int id) {
        Optional<ProductModel> productModel = productRepository.findById(id);
        return productModel;
    }

    @Transactional
    public ProductDTO update(ProductModel productModel) {
        var existingProduct = findProduct(productModel.getId());

        if(existingProduct.isEmpty()){
            log.error("Product with id {} doesn't exist ", productModel.getId());
        }
        if(productModel.getId() == 0) {
            log.error("Cannot update product without a valid id");
        }
        ProductModel updatedProductModel = productRepository.save(productModel);
        ProductDTO productDTO = ProductMapper.productMapper.toDTO(updatedProductModel);

        return productDTO;
    }

    /* ****
     *
     * Description : get a product by id
     * Helpers : findProduct(Integer id) method check if product already exists
     * @param : product id
     *
     **** */
    public ProductDTO read(Integer id) {
        Optional<ProductModel> productModel = findProduct(id);
        if(productModel.isPresent()) {
            ProductModel product = productModel.get();

            ProductDTO productDTO = ProductMapper.productMapper.toDTO(product);

            return productDTO;
        }else {
            return null;
        }
    }


    /* ****
     *
     * Description : fetches all categories
     * Helpers : findCategory(Integer id) method check if market category with given email already exists
     *
     **** */

    public List<ProductDTO> readAll() {
        List<ProductModel> products = productRepository.findAll();
        if(!products.isEmpty()) {
            List<ProductDTO> productDTOS = new ArrayList<>();
            products.forEach(product -> {
                product.setPromotion(null);
                product.setCategory(null);
                productDTOS.add(ProductMapper.productMapper.toDTO(product));
            });
            return productDTOS;
        }
        return null;
    }

    /* ****
     *
     * Description : delete product
     * Helpers : findProduct(Integer id) method check if product already exists
     * @param : id
     *
     **** */

    public ResponseEntity<Void> delete(Integer id) {
        var existingProduct = findProduct(id);

        if(existingProduct.isEmpty()) {
            log.error("Product with id " + id+ " doesn't exist'");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(existingProduct.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
