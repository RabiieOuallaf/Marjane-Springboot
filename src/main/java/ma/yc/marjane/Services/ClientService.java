package ma.yc.marjane.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.ClientDTO;
import ma.yc.marjane.Mappers.ClientMapper;
import ma.yc.marjane.Models.ClientModel;
import ma.yc.marjane.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    /*
    * Description : creates a new client
    * @Param : ClientModel
    * Helpers : find(int clientId)
     */

    public ClientDTO create(ClientModel clientModel) {

        if(clientModel != null) {
            ClientModel foundClient = findById(clientModel.getId());
            if(foundClient != null) {
                ClientModel client = clientRepository.save(clientModel);
                return ClientMapper.clientMapper.toDTO(client);
            }else {
                log.warn("Client already exists!");
                return null;
            }

        }else {
            log.error("Client credentials are required!");
            return null;
        }
    }

    /*
     * Description : find a client
     * @Param : int clientId
     */
    public ClientModel findById(int clientId) {
        if(clientId != 0) {
            Optional<ClientModel> clientModel = clientRepository.findById(clientId);
            return clientModel.orElse(null);
        }else {
            log.error("Client credentials are wrong!");
            return null;
        }
    }

    /*
     * Description : update an existing client
     * @Param : ClientModel
     * Helpers : find(int clientId)
     */

    public ClientDTO update(ClientModel clientModel) {
        if(clientModel != null) {
            ClientModel foundClient = findById(clientModel.getId());
            if(foundClient != null) {
                ClientModel updatedClinet = clientRepository.save(clientModel);
                ClientDTO clientDTO = ClientMapper.clientMapper.toDTO(updatedClinet);
                return clientDTO;
            }else {
                log.warn("There's no client with corresponding credentials!");
                return null;
            }
        }else {
            log.error("Client credentials are wrong!");
            return null;
        }
    }

    /*
     * Description : read an existing client by email
     * @Param : String email
     * Helpers : findByEmail(String email)
     */

    public ClientDTO read(String email) {
        ClientModel clientModel = findByEmail(email);
        if(clientModel != null) {
            return ClientMapper.clientMapper.toDTO(clientModel);
        }else {
            log.warn("Currently , there's no client with email " + email);
            return null;
        }
    }

    /*
     * Description : this method returns a list of clients
     */

    public List<ClientDTO> readAll() {
        List<ClientModel> clientModelList = clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clientModelList.forEach(clientModel -> {
            ClientDTO clientDTO = ClientMapper.clientMapper.toDTO(clientModel);
           clientDTOList.add(clientDTO);
        });
        return clientDTOList;
    }

    /*
    * Description : this is a helper method to retrieve a client by its find by email
     */
    public ClientModel findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void delete(String email) {
        ClientModel clientModel = clientRepository.findByEmail(email);
        if(clientModel == null) {
            log.warn("Couldn't find client to delete");
        }else {
            clientRepository.deleteByEmail(email);
        }
    }

}
