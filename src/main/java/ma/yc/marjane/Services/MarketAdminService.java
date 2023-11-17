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

    /* ****
    *
    * Description : create a new MarketAdmin (Register it)
    * Helpers : findMarketAdmin method check if market admin with given email already exists
    * @param : MarketAdminEntity(Model) as a parameter
    *
     **** */

    @Autowired
    private final MarketAdminRepository marketAdminRepository;

    @Transactional
    public Optional<MarketAdminModel> create(MarketAdminModel marketAdmin){
        var marketAdminExists = findMarketAdmin(marketAdmin.getEmail());

        if(marketAdminExists.isPresent()) {
            log.error("Market admin with email {} already exists", marketAdmin.getEmail());
        }

        MarketAdminModel createdMarketAdminModel = marketAdminRepository.save(marketAdmin);
        return Optional.of(createdMarketAdminModel);
    }

    private Optional<MarketAdminModel> findMarketAdmin(String email) {
        Optional<MarketAdminModel> marketAdminModel = marketAdminRepository.findByEmail(email);
        return marketAdminModel;
    }


    /* ****
     *
     * Description : update a new MarketAdmin (Register it)
     * @param : MarketAdminEntity(Model) as a parameter
     *
     **** */


    @Transactional
    public Optional<MarketAdminModel> update(MarketAdminModel marketAdmin){
        var marketAdminExists = findMarketAdmin(marketAdmin.getEmail());

        if(marketAdminExists.isEmpty()){
            log.error("Market admin with email " + marketAdmin.getEmail() + "doesn't exist'");
            return Optional.empty();
        }

        if(marketAdmin.getId() == 0){
            log.error("Cannot delete Market admin without a valid id");
        }
        MarketAdminModel marketAdminModel = marketAdminRepository.save(marketAdmin);
        return Optional.of(marketAdminModel);
    }

    /* ****
     *
     * Description : update a new MarketAdmin (Register it)
     * @param : Email
     *
     **** */

    @Transactional
    public ResponseEntity<Void> delete(String email ){
        System.out.println("Service email" + email);

        var marketAdminExists = findMarketAdmin(email);

        if(marketAdminExists.isEmpty()){
            log.error("Market admin with email " + email+ "doesn't exist'");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        marketAdminRepository.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* ****
    *
    * Description : get a user by email
    * @param : Email address
    *
      **** */

    public ResponseEntity<Optional<MarketAdminModel>> read(String email) {
        Optional<MarketAdminModel> marketAdminModel = marketAdminRepository.findByEmail(email);
        System.out.println(marketAdminModel + "<== Heere");
        if (marketAdminModel.isPresent()) {
            return ResponseEntity.ok().body(marketAdminModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* ****
     *
     * Description : get a list of users
     * @param : Email address
     *
     **** */
    public ResponseEntity<List<MarketAdminModel>> readAll() {
        System.out.println("I reached here as well , In the service");
        List<MarketAdminModel> marketAdmins = marketAdminRepository.findAll();
        System.out.println("I don't think i'll reach here too "+ " " + marketAdmins);
        return ResponseEntity.ok().body(marketAdmins);
    }

}
