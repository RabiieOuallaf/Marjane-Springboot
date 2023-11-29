package ma.yc.marjane.Controllers.Implementation.Client;

import lombok.RequiredArgsConstructor;
import ma.yc.marjane.DTO.ClientDTO;
import ma.yc.marjane.Models.ClientModel;
import ma.yc.marjane.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor

public class ClientController {
    @Autowired
    private final ClientService clientService;

    /* ****
     *
     * POST /api/v1/client/create
     * Definition : this method creates a new client
     * @Param : ClientModel
     *
     **** */
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ClientModel clientModel) {
        ClientDTO clientDTO = clientService.create(clientModel);
        if(clientDTO != null) {
            return ResponseEntity.ok("Client created successfully " + clientDTO);
        }else {
            return ResponseEntity.badRequest().body("Client creation failed");
        }
    }

    /* ****
     *
     * Put /api/v1/client/update
     * Definition : this method update a client
     * @Param : ClientModel
     *
     **** */
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody ClientModel clientModel) {
        ClientDTO clientDTO = clientService.create(clientModel);
        if(clientModel != null) {
            return ResponseEntity.ok("Client created "+ clientDTO);
        }else {
            return ResponseEntity.badRequest().body("Client creation failed");
        }
    }

    /* ****
     *
     * Get /api/v1/client/read/{param}
     * Definition : this method reads a client
     * @Param : String email
     *
     **** */
    @GetMapping("/read/{email}")
    public ResponseEntity<String> read(@PathVariable String email) {
        ClientDTO clientDTO = clientService.read(email);
        if(clientDTO != null) {
            return ResponseEntity.ok("Cashier agent : "+ clientDTO);
        }else {
            return ResponseEntity.badRequest().body("There's mo client with email " + email);
        }
    }

    /* ****
     *
     * Get /api/v1/client/readAll
     * Definition : this method returns a list of all clients
     *
     **** */

    @GetMapping("/readAll")
    public ResponseEntity<List<ClientDTO>> readAll() {
        List<ClientDTO> clientDTOS = clientService.readAll();

        if(clientDTOS.isEmpty()) {
            return ResponseEntity.ok(clientDTOS);
        }else {
            return ResponseEntity.badRequest().body(clientDTOS);
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
        clientService.delete(email);
        return ResponseEntity.ok().body("Client with email : " + email + " is deleted successfully");
    }

}
