package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.PromotionDTO;
import ma.yc.marjane.Models.PromotionModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionMapper promotionMapper = Mappers.getMapper(PromotionMapper.class);

    PromotionDTO toDTO(PromotionModel promotionModel);
    PromotionModel toEntity(PromotionDTO promotionDTO);
}
