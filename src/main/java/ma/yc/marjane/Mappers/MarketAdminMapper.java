package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.MarketAdminDTO;
import ma.yc.marjane.Models.MarketAdminModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarketAdminMapper {
    MarketAdminMapper marketAdminMapper = Mappers.getMapper(MarketAdminMapper.class);

    MarketAdminDTO toDTO(MarketAdminModel marketAdminModel);
    MarketAdminModel toEntity(MarketAdminDTO marketAdminDTO);
}
