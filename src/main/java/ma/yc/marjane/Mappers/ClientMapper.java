package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.ClientDTO;
import ma.yc.marjane.Models.ClientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(ClientModel clientModel);
    ClientModel toEntity(ClientDTO clientDTO);

}
