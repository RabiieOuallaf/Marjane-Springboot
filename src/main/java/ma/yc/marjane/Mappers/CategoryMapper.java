package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.CategoryDTO;
import ma.yc.marjane.Models.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    CategoryModel toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(CategoryModel categoryModel);

}
