package ma.yc.marjane.Controllers.Implementation.Adminstration;

import ma.yc.marjane.Models.GeneralAdminModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/general-admin")
public class GeneralAdminController {

    @PostMapping("/authenticate")
    public String Authenticate(@RequestBody GeneralAdminModel generalAdmin) {
        return null;
    }
    @GetMapping("/test")
    public void test() {
        System.out.println("tessst");
    }
}
