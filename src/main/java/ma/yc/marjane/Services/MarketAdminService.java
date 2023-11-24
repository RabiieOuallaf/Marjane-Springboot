package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.Models.MarketAdminModel;
import ma.yc.marjane.Repositories.MarketAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketAdminService {



    @Autowired
    private final MarketAdminRepository marketAdminRepository;

    /* ****
     *
     * Description : create a new MarketAdmin (Register it)
     * Helpers : findMarketAdmin method check if market admin with given email already exists
     * @param : MarketAdminEntity(Model) as a parameter
     *
     **** */

    @Transactional
    public MarketAdminModel create(MarketAdminModel marketAdmin){
        var marketAdminExists = findMarketAdmin(marketAdmin.getEmail());

        if(marketAdminExists != null) {
            log.error("Market admin with email {} already exists", marketAdmin.getEmail());
        }

        MarketAdminModel createdMarketAdminModel = marketAdminRepository.save(marketAdmin);
        return createdMarketAdminModel;
    }

    private MarketAdminModel findMarketAdmin(String email) {
        MarketAdminModel marketAdminModel = marketAdminRepository.findByEmail(email);
        return marketAdminModel;
    }


    /* ****
     *
     * Description : update a new MarketAdmin (Register it)
     * @param : MarketAdminEntity(Model) as a parameter
     *
     **** */

    @Transactional
    public MarketAdminModel update(MarketAdminModel marketAdmin){
        var marketAdminExists = findMarketAdmin(marketAdmin.getEmail());

        if(marketAdminExists != null){
            log.error("Market admin with email " + marketAdmin.getEmail() + "doesn't exist'");
            return null;
        }

        if(marketAdmin.getId() == 0){
            log.error("Cannot delete Market admin without a valid id");
        }
        MarketAdminModel marketAdminModel = marketAdminRepository.save(marketAdmin);
        return marketAdminModel;
    }

    /* ****
     *
     * Description : delete category
     * @param : Email
     *
     **** */

    @Transactional
    public Void delete(String email ){

        var marketAdminExists = findMarketAdmin(email);

        if(marketAdminExists != null){
            log.error("Market admin with email " + email+ "doesn't exist'");
        }
        marketAdminRepository.deleteByEmail(email);
        return null;
    }

    /* ****
    *
    * Description : get a user by email
    * @param : Email address
    *
      **** */

    public MarketAdminModel read(String email) {
        MarketAdminModel marketAdminModel = marketAdminRepository.findByEmail(email);

        if(marketAdminModel != null){
            return marketAdminModel;
        }else {
            return null;
        }
    }

    /* ****
     *
     * Description : get a list of users
     * @param : Email address
     *
     **** */
    public List<MarketAdminModel> readAll() {
        List<MarketAdminModel> marketAdmins = marketAdminRepository.findAll();
        return marketAdmins;
    }

}
