package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.CashierAgentDTO;
import ma.yc.marjane.Mappers.CashierAgentMapper;
import ma.yc.marjane.Models.CashierAgentModel;
import ma.yc.marjane.Repositories.CashierAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashierAgentService {
    @Autowired
    private final CashierAgentRepository cashierAgentRepository;

    /* ****
     *
     * Description : create a new cashier agent
     * Helpers : findCashierAgent(String email) method check if  already exists
     * @param : CashierAgent(Model) as a parameter
     *
     **** */
    @Transactional
    public CashierAgentDTO create(CashierAgentModel cashierAgentModel){
        var cashierAgent = findCashierAgent(cashierAgentModel.getEmail());
        if(cashierAgent != null) {
            log.error("Cashier agent with email {} already exists ", cashierAgent.getId());
        }

        CashierAgentModel createdCashierAgentModel = cashierAgentRepository.save(cashierAgentModel);

        return CashierAgentMapper.cashierAgentMapper.toDTO(createdCashierAgentModel);
    }

    private CashierAgentModel findCashierAgent(String email) {
        CashierAgentModel cashierAgentModel = cashierAgentRepository.findByEmail(email);
        if(cashierAgentModel != null) {
            return cashierAgentModel;
        }else {
            log.warn("Could not find cashier agent");
            return null;
        }
    }

    /* ****
     *
     * Description : update a cashier agent
     * Helpers : findCashierAgent(String email) method check if  already exists
     * @param : CashierAgent(Model) as a parameter
     *
     **** */

    @Transactional
    public CashierAgentDTO update(CashierAgentModel cashierAgentModel){
        var cashierAgent = findCashierAgent(cashierAgentModel.getEmail());
        if(cashierAgent == null) {
            log.error("Cashier agent doesn't exist ");
        }
        CashierAgentModel createdCashierAgentModel = cashierAgentRepository.save(cashierAgentModel);
        return CashierAgentMapper.cashierAgentMapper.toDTO(createdCashierAgentModel);
    }

    /* ****
     *
     * Description : read a cashier agent
     * Helpers : findCashierAgent(String email) method check if  already exists
     * @param : String email
     *
     **** */
    public CashierAgentDTO read(String email){
        var cashierAgent = findCashierAgent(email);
        if(cashierAgent == null) {
            log.error("Cashier agent with email {} doesn't exist ", cashierAgent.getId());
            return null;
        }
        assert(cashierAgent != null);
        return CashierAgentMapper.cashierAgentMapper.toDTO(cashierAgent);
    }

    /* ****
     *
     * Description : read a list of existing cashier agents
     *
     **** */

    public List<CashierAgentDTO> readAll() {
        List<CashierAgentModel> cashierAgentModelList = cashierAgentRepository.findAll();
        List<CashierAgentDTO> cashierAgentDTOList = new ArrayList<CashierAgentDTO>();
        if(cashierAgentModelList.size() > 0) {
            cashierAgentModelList.forEach(cashierAgentModel -> {
                CashierAgentDTO cashierAgentDTO = CashierAgentMapper.cashierAgentMapper.toDTO(cashierAgentModel);
                cashierAgentDTOList.add(cashierAgentDTO);
            });
            return cashierAgentDTOList;
        }else {
            log.warn("No cashier agent found");
            return null;
        }
    }
    /* ****
     *
     * Description : delete cashier agent
     * Helpers : findProduct(Integer id) method check if product already exists
     * @param : id
     *
     **** */

    @Transactional
    public void delete(String email) {
        var existingCashier = findCashierAgent(email);

        if(existingCashier == null){
            log.warn("No cashier agent found to be deleted !");
        }else {
            cashierAgentRepository.delete(existingCashier);
        }
    }


}
