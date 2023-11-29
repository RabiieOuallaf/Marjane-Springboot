package ma.yc.marjane.Controllers.Implementation.CashierAgent;

import lombok.RequiredArgsConstructor;
import ma.yc.marjane.DTO.CashierAgentDTO;
import ma.yc.marjane.Models.CashierAgentModel;
import ma.yc.marjane.Services.CashierAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cashier-agent")
@RequiredArgsConstructor

public class CashierAgentController {
    @Autowired
    private final CashierAgentService cashierAgentService;

    /* ****
     *
     * POST /api/v1/cashier-agent/create
     * Definition : this method creates a new agent
     * @Param : CashierAgentModel
     *
     **** */

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CashierAgentModel cashierAgentModel) {
        CashierAgentDTO cashierAgentDTO = cashierAgentService.create(cashierAgentModel);
        if(cashierAgentDTO != null) {
            return ResponseEntity.ok("Cashier agent created "+cashierAgentDTO);
        }else {
            return ResponseEntity.badRequest().body("Cashier agent creating failed");
        }
    }

    /* ****
     *
     * Put /api/v1/cashier-agent/update
     * Definition : this method updates an agent
     * @Param : CashierAgentModel
     *
     **** */
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody CashierAgentModel cashierAgent){
        CashierAgentDTO cashierAgentDTO = cashierAgentService.update(cashierAgent);

        if(cashierAgentDTO != null) {
            return ResponseEntity.ok("Cashier agent is updated "+cashierAgentDTO);
        }else {
            return ResponseEntity.badRequest().body("Cashier agent updating failed");
        }
    }

    /* ****
     *
     * Get /api/v1/cashier-agent/read/{param}
     * Definition : this method reads an agent
     * @Param : String email
     *
     **** */

    @GetMapping("/read/{email}")
    public ResponseEntity<String> read(@PathVariable String email) {
        CashierAgentDTO cashierAgentDTO = cashierAgentService.read(email);
        if(cashierAgentDTO != null) {
            return ResponseEntity.ok("Cashier agent : "+cashierAgentDTO);
        }else {
            return ResponseEntity.badRequest().body("There's no agent with email " + email);
        }
    }

    /* ****
     *
     * Get /api/v1/cashier-agent/readAll
     * Definition : this method returns a list of all agents
     *
     **** */

    @GetMapping("/readAll")
    public ResponseEntity<List<CashierAgentDTO>> readAll() {
        List<CashierAgentDTO> cashierAgentDTOs = cashierAgentService.readAll();

        if(cashierAgentDTOs.isEmpty()) {
            return ResponseEntity.ok(cashierAgentDTOs);
        }else {
            return ResponseEntity.badRequest().body(cashierAgentDTOs);
        }
    }

    /* ****
     *
     * DELETE /api/v1/cashier-agent/delete
     * Definition : this method deletes an agents
     *
     **** */

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> delete(@PathVariable String email) {
        cashierAgentService.delete(email);
        return ResponseEntity.ok().body("Cashier agent with email : " + email + " is deleted successfully");
    }


}
