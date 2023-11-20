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

    /* ****
     * PUT /api/v1/market-admin/update
     * Request body : MarketAdminModel
     * Description : update an existing admin market
     *
     * ****/

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody MarketAdminModel marketAdmin){
        Optional<MarketAdminModel> updatedMarketAdminModel = marketAdminService.update(marketAdmin);

        if(updatedMarketAdminModel.isPresent()) {
            return ResponseEntity.ok("Market admin updated successfully");
        }else {
            return ResponseEntity.badRequest().body("Market admin updating failed, check the logs for more details");
        }
    }

    /* ****
     * POST /api/v1/market-admin/readAll
     * Request body : none
     * Description : returns a list of market admins
     *
     * ****/

    @GetMapping("/readAll")
    public ResponseEntity<List<MarketAdminModel>> readAll() {
        List<MarketAdminModel> marketAdmins = (List<MarketAdminModel>) marketAdminService.readAll();

        return ResponseEntity.ok().body(marketAdmins);
    }

    /* ****
     * GET /api/v1/market-admin/read/{param}
     * @param : String email
     * Description : fetchs a market admin
     *
     * ****/

    @GetMapping("/read/{email}")
    public ResponseEntity<Optional<MarketAdminModel>> read(@PathVariable String email) {
        ResponseEntity<Optional<MarketAdminModel>> marketAdminModel = marketAdminService.read(email);
            return ResponseEntity.ok().body(marketAdminModel.getBody());
    }

    /* ****
     * DELETE /api/v1/market-admin/delete/{email}
     * @param : String email
     * Description : Delete an admin market
     *
     * ****/

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email){
         marketAdminService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* ****
     * post /api/v1/market-admin/accept-promotion/{id}
     * @param : String id
     * Description : Accept promotion
     *
     * ****/

//    @PostMapping("/accept/{id}")
//    public ResponseEntity<Void> accept(@PathVariable String id) {
//
//    }
}
