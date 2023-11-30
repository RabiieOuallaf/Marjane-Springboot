package ma.yc.marjane.Controllers.Implementation.Auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.Auth.JwtUtil;
import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.DTO.LoginReqDTO;
import ma.yc.marjane.DTO.LoginResDTO;
import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.GeneralAdminModel;
import ma.yc.marjane.Models.MarketAdminModel;
import ma.yc.marjane.Models.RayonAdminModel;
import ma.yc.marjane.Services.GeneralAdminService;
import ma.yc.marjane.Services.MarketAdminService;
import ma.yc.marjane.Services.RayonAdminService;
import ma.yc.marjane.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final GeneralAdminService generalAdminService;
    private final MarketAdminService marketAdminService;
    private final RayonAdminService rayonAdminService;
    private final UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> performAuthentication(@RequestBody LoginReqDTO loginReqDTO) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReqDTO.getEmail());
        if (Objects.equals(loginReqDTO.getPassword(), userDetails.getPassword())) {
            String email = userDetails.getUsername();

            if (loginReqDTO.getType().equals("general")) {
                GeneralAdminDTO generalAdmin = generalAdminService.read(email);
                if (generalAdmin != null) {

                    GeneralAdminModel generalAdminModel = new GeneralAdminModel().builder().email(generalAdmin.getEmail()).role(generalAdmin.getRole()).build();
                    String token = jwtUtil.createToken(generalAdminModel);
                    LoginResDTO loginResDTO = new LoginResDTO(email, token);

                    return ResponseEntity.ok(loginResDTO);
                }
            }
            if (loginReqDTO.getType().equals("rayon")) {
                RayonAdminDTO rayonAdminDTO = rayonAdminService.read(email);
                if (rayonAdminDTO != null) {
                    RayonAdminModel rayonAdminModel = new RayonAdminModel().builder().email(email).build();
                    String token = jwtUtil.createToken(rayonAdminModel);
                    LoginResDTO loginResDTO = new LoginResDTO(email, token);
                    return ResponseEntity.ok(loginResDTO);
                }
            }
            if (loginReqDTO.getType().equals("market")) {
                MarketAdminModel marketAdminModel = marketAdminService.read(email);
                if (marketAdminModel != null) {
                    MarketAdminModel builtMarketAdminModel = new MarketAdminModel().builder().email(email).build();
                    String token = jwtUtil.createToken(builtMarketAdminModel);
                    LoginResDTO loginResDTO = new LoginResDTO(email, token);

                    return ResponseEntity.ok(loginResDTO);
                }
            }

        } else {
            log.warn("Invalid password");
            return null;
        }
        return null;
    }


}
