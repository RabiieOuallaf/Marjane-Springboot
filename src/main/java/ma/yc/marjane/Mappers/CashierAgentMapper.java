package ma.yc.marjane.Mappers;

import ma.yc.marjane.DTO.CashierAgentDTO;
import ma.yc.marjane.Models.CashierAgentModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface CashierAgentMapper {
    CashierAgentMapper cashierAgentMapper = Mappers.getMapper(CashierAgentMapper.class);

    CashierAgentDTO toDTO(CashierAgentModel model);
    CashierAgentModel toEntity(CashierAgentDTO cashierAgentDTO);
}
