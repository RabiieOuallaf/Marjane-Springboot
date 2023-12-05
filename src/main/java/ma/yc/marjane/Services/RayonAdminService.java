package ma.yc.marjane.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Mappers.RayonAdminMapper;
import ma.yc.marjane.Models.RayonAdminModel;
import ma.yc.marjane.Repositories.RayonAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RayonAdminService {

    @Autowired
    private final RayonAdminRepository rayonAdminRepository;

    /* ****
     *
     * Description : create a new rayon admin
     * Helpers : findRayonAdmin(String name) method check if market category with given email already exists
     * @param : RayonAdminEntity(Model) as a parameter
     *
     **** */

    public RayonAdminDTO create(RayonAdminModel rayonAdminModel) {
        var existingRayonAdmin = findRayonAdmin(rayonAdminModel.getEmail());

        if(existingRayonAdmin != null) {
            log.error("Admin with id {} already exists", rayonAdminModel.getId());
        }

        RayonAdminModel rayonAdmin = rayonAdminRepository.save(rayonAdminModel);
        RayonAdminDTO rayonAdminDTO = RayonAdminMapper.rayonAdminMapper.toDTO(rayonAdmin);
        return rayonAdminDTO;

    }
    private Optional<RayonAdminModel> findRayonAdmin(String email) {
        Optional<RayonAdminModel> rayonAdminModel = rayonAdminRepository.findByEmail(email);
        return rayonAdminModel;

    }

    /* ****
     *
     * Description : update a RayonAdmin
     * Helpers : findRayonAdmin(String email) method check if rayon category with given email already exists
     * @param : RayonAdminEntity(Model) as a parameter
     *
     **** */

    public RayonAdminDTO update(RayonAdminModel rayonAdminModel) {
        var existingRayonAdmin = findRayonAdmin(rayonAdminModel.getEmail());

        if(existingRayonAdmin == null) {
            log.error("Admin with id {} does not exist", rayonAdminModel.getId());
        }

        RayonAdminModel rayonAdmin = rayonAdminRepository.save(rayonAdminModel);
        RayonAdminDTO rayonAdminDTO = RayonAdminMapper.rayonAdminMapper.toDTO(rayonAdmin);
        return rayonAdminDTO;
    }

    /* ****
     *
     * Description : get an admin by email
     * Helpers : findRayonAdmin(String email) method check if rayon admin  with given email already exists
     * @param : admin email
     *
     **** */
    public RayonAdminDTO read(String email) {
        Optional<RayonAdminModel> rayonAdminModel = findRayonAdmin(email);

        if(rayonAdminModel.isPresent()){
            RayonAdminModel rayonAdminClass = rayonAdminModel.get();
            RayonAdminDTO rayonAdminDTO = RayonAdminMapper.rayonAdminMapper.toDTO(rayonAdminClass);
            return rayonAdminDTO;

        } else {
            return null;
        }
    }
    public RayonAdminModel readModel(String email) {
        Optional<RayonAdminModel> rayonAdminModel = findRayonAdmin(email);

        if(rayonAdminModel.isPresent()){
             RayonAdminModel rayonAdminModelClass = rayonAdminModel.get();
            return rayonAdminModelClass;
        } else {
            return null;
        }
    }

    /* ****
     *
     * Description : delete rayon admin
     * Helpers : findRayonAdmin(String email) method check if rayon admin with given email already exists
     * @param : email
     *
     **** */
    @Transactional
    public ResponseEntity<Void> delete(String email ){

        var rayonAdminExists = findRayonAdmin(email);

        if(rayonAdminExists.isEmpty()){
            log.error("Rayon admin with email " + email+ "doesn't exist'");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rayonAdminRepository.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /* ****
     *
     * Description : get a list of users
     * @param : Email address
     *
     **** */
    public List<RayonAdminDTO> readAll() {

        List<RayonAdminModel> rayonAdmins = rayonAdminRepository.findAll();

        if(!rayonAdmins.isEmpty()) {
            List<RayonAdminDTO> rayonAdminDTOS = new ArrayList<>();

            rayonAdmins.forEach(rayonAdmin -> {
                rayonAdminDTOS.add(RayonAdminMapper.rayonAdminMapper.toDTO(rayonAdmin));
            });
            return rayonAdminDTOS;

        }
        return null;
    }
}
