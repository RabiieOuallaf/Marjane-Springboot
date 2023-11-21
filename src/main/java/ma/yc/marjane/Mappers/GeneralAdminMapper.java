package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.Models.GeneralAdminModel;
import ma.yc.marjane.Models.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GeneralAdminMapper {
    GeneralAdminMapper generalAdminMapper = Mappers.getMapper(GeneralAdminMapper.class);

    GeneralAdminDTO toDTO(GeneralAdminModel generalAdminModel);
    GeneralAdminModel toEntity(GeneralAdminDTO generalAdminDTO);
}
