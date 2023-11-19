package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.Models.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    ProductDTO toDTO(ProductModel product);
    ProductModel toEntity(ProductDTO product);
}
