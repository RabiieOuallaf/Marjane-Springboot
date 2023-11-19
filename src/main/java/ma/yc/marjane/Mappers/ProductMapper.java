package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.Models.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(ProductModel product);
    ProductModel toEntity(ProductDTO product);
}
