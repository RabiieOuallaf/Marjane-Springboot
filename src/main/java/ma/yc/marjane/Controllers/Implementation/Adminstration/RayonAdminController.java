package ma.yc.marjane.Controllers.Implementation.Adminstration;

import ma.yc.marjane.Models.RayonAdminModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rayon-admin")
public class RayonAdminController {
    @PostMapping("/create")
    public RayonAdminModel create(@RequestBody RayonAdminModel rayonAdmin){
        return null;
    }

    @PutMapping("/put")
    public RayonAdminModel update(@RequestBody RayonAdminModel rayonAdmin){
        return null;
    }
    @GetMapping("/readAll")
    public List<RayonAdminModel> readAll(){
        return null;
    }
    @GetMapping("/read")
    public RayonAdminModel read(@RequestBody String email){
        return null;
    }
    @DeleteMapping("/delete")
    public RayonAdminModel delete(@RequestBody String email){
        return null;
    }
}
