package ma.yc.marjane.Controllers.Implementation.Adminstration;

import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.RayonAdminModel;
import ma.yc.marjane.Services.RayonAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rayon-admin")
public class RayonAdminController {

    @Autowired
    private final RayonAdminService rayonAdminService;

    public RayonAdminController(RayonAdminService rayonAdminService) {
        this.rayonAdminService = rayonAdminService;
    }




    /* ****
     *
     * POST /api/v1/rayon-admin/create
     * Definition : this method creates a new rayon admin
     * @Param : RayonAdminModel
     *
     **** */
    @PreAuthorize("hasAuthority('CREATE_RAYON_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RayonAdminModel rayonAdmin){
        RayonAdminDTO createdRayonAdmin = rayonAdminService.create(rayonAdmin);
        System.out.println(rayonAdmin.getFullname());
        System.out.println(createdRayonAdmin.getFullname() + "<== created");
        if(createdRayonAdmin != null){
            return ResponseEntity.ok("Created successfully : "+ createdRayonAdmin);
        }else {
            return ResponseEntity.badRequest().body("Admin creation failed, check logs for more details");
        }
    }
    /* ****
     * PUT /api/v1/category/update
     * Request body : CategoryModel
     * Description : update an existing admin
     *
     * ****/
    @PreAuthorize("hasAuthority('UPDATE_RAYON_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody RayonAdminModel rayonAdmin){
        RayonAdminDTO createdRayonAdmin = rayonAdminService.update(rayonAdmin);

        if(createdRayonAdmin != null){
            return ResponseEntity.ok("Updated successfully : "+ createdRayonAdmin);
        }else {
            return ResponseEntity.badRequest().body("Admin creation failed, check logs for more details");
        }
    }
    /* ****
     * POST /api/v1/category/readAll
     * Request body : none
     * Description : returns a list of admin
     *
     * ****/
    @PreAuthorize("hasAuthority('READ_RAYON_ADMIN')")
    @GetMapping("/readAll")
    public ResponseEntity<List<RayonAdminDTO>> readAll(){
        List<RayonAdminDTO> rayonAdmins = rayonAdminService.readAll();

        return ResponseEntity.ok().body(rayonAdmins);
    }
    /* ****
     * POST /api/v1/category/read
     * Request body : email
     * Description : returns an admin
     *
     * ****/
    @PreAuthorize("hasAuthority('READ_RAYON_ADMIN')")

    @GetMapping("/read/{email}")
    public ResponseEntity<String> read(@PathVariable String email){
        RayonAdminDTO rayonAdminDTO = rayonAdminService.read(email);

        if(rayonAdminDTO != null){
            return ResponseEntity.ok().body("Rayon admin : "+rayonAdminDTO);
        }else {
            return ResponseEntity.badRequest().body("Admin not found");
        }
    }
    /* ****
     * DELETE /api/v1/category/read
     * Request body : email
     * Description : deletes an admin
     *
     * ****/
    @PreAuthorize("hasAuthority('DELETE_RAYON_ADMIN')")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email){
        rayonAdminService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
