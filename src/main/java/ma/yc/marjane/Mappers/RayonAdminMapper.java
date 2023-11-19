package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.RayonAdminModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RayonAdminMapper {
    RayonAdminMapper rayonAdminMapper = Mappers.getMapper(RayonAdminMapper.class);

    RayonAdminDTO toDTO(RayonAdminModel rayonModel);

    RayonAdminModel toEntity(RayonAdminDTO rayonDTO);
}
