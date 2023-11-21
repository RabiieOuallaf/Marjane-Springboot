package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.MarjaneMarketDTO;
import ma.yc.marjane.Models.MarjaneMarketModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarjaneMarketMapper {
    MarjaneMarketMapper marjaneMarketMapper = Mappers.getMapper(MarjaneMarketMapper.class);

    MarjaneMarketDTO toDTO(MarjaneMarketModel marjaneMarketModel);
    MarjaneMarketModel toEntity(MarjaneMarketDTO marjaneMarketDTO);
}
