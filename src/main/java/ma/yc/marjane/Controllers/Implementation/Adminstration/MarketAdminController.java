package ma.yc.marjane.Controllers.Implementation.Adminstration;

import ma.yc.marjane.Models.MarketAdminModel;
import ma.yc.marjane.Services.MarketAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/market-admin")
public class MarketAdminController {
    private final MarketAdminService marketAdminService;

    @Autowired
    public MarketAdminController(MarketAdminService marketAdminService) {
        this.marketAdminService = marketAdminService;
    }


    @PostMapping("/authenticate")
    public MarketAdminModel authenticate(@RequestBody MarketAdminModel marketModel) {
        return null;
    }

    /* ****
    * POST /api/v1/market-admin/create
    * Request body : MarketAdminModel
    * Description : create a new admin market
    *
     * ****/

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody MarketAdminModel marketAdmin){
        Optional<MarketAdminModel> createdMarketAdmin= marketAdminService.create(marketAdmin);
        if(createdMarketAdmin.isPresent()){
            return ResponseEntity.ok("Market Admin with email : " + marketAdmin.getEmail() + " created successfully");
        }else {
            return ResponseEntity.badRequest().body("Market admin creation failed , check logs for more details");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody MarketAdminModel marketAdmin){
        Optional<MarketAdminModel> updatedMarketAdminModel = marketAdminService.update(marketAdmin);

        if(updatedMarketAdminModel.isPresent()) {
            return ResponseEntity.ok("Market admin updated successfully");
        }else {
            return ResponseEntity.badRequest().body("Market admin updating failed, check the logs for more details");
        }
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<MarketAdminModel>> readAll() {
        System.out.println("I reached here!");
        List<MarketAdminModel> marketAdmins = (List<MarketAdminModel>) marketAdminService.readAll();
        System.out.println("List of market admins" + marketAdmins);

        return ResponseEntity.ok().body(marketAdmins);
    }

    @GetMapping("/read/{email}")
    public ResponseEntity<Optional<MarketAdminModel>> read(@PathVariable String email) {
        System.out.println("email: " + email);
        ResponseEntity<Optional<MarketAdminModel>> marketAdminModel = marketAdminService.read(email);
            return ResponseEntity.ok().body(marketAdminModel.getBody());

    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email){
         marketAdminService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
