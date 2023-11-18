package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.CategoryDTO;
import ma.yc.marjane.Models.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    CategoryModel convertToEntity(CategoryDTO categoryDTO);

    CategoryDTO convertToCategoryDTO(CategoryModel categoryModel);

}
