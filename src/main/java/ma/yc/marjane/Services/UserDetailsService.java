package ma.yc.marjane.Services;

import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.MarketAdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final GeneralAdminService generalAdminService;
    private final MarketAdminService marketAdminService;
    private final RayonAdminService rayonAdminService;

    @Autowired
    public UserDetailsService(GeneralAdminService generalAdminService, MarketAdminService marketAdminService, RayonAdminService rayonAdminService) {
        this.generalAdminService = generalAdminService;
        this.marketAdminService = marketAdminService;
        this.rayonAdminService = rayonAdminService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        GeneralAdminDTO generalAdminDTO = generalAdminService.read(email);
        RayonAdminDTO rayonAdminDTO = rayonAdminService.read(email);
        ResponseEntity<Optional<MarketAdminModel>> marketAdminModel = marketAdminService.read(email);

        if(generalAdminDTO != null) {

            UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(generalAdminDTO.getEmail())
                        .password(generalAdminDTO.getPassword())
                        .roles("GENERAL_ADMIN")
                        .build();
            System.out.println(userDetails + "<== General admin");
            return userDetails;
        }else if(rayonAdminDTO != null) {
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(rayonAdminDTO.getEmail())
                            .password(rayonAdminDTO.getPassword())
                            .roles("RAYON_ADMIN")
                            .build();
            System.out.println(rayonAdminDTO + "<== Rayon admin");

            return userDetails;


//        }else if(marketAdminModel != null) {
//            UserDetails userDetails =
//                    org.springframework.security.core.userdetails.User.builder()
//                            .username(marketAdminModel.getEmail())
//                            .password(marketAdminModel.getPassword())
//                            .roles("MARKET_ADMIN")
//                            .build();
//            return userDetails;
        }else {
            throw new UsernameNotFoundException("User not found with email" + email);
        }
    }

}
