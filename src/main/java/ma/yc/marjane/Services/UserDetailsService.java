package ma.yc.marjane.Services;

import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.MarketAdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final GeneralAdminService generalAdminService;
    private final MarketAdminService marketAdminService;
    private final RayonAdminService rayonAdminService;

    private final String MARKET_ADMIN_PREFIX = "MARKET_ADMIN";
    private final String RAYON_ADMIN_PREFIX = "RAYON_ADMIN";


    @Autowired
    public UserDetailsService(GeneralAdminService generalAdminService, MarketAdminService marketAdminService, RayonAdminService rayonAdminService) {
        this.generalAdminService = generalAdminService;
        this.marketAdminService = marketAdminService;
        this.rayonAdminService = rayonAdminService;
    }

    /*
    *
    * @param String method name, prefix
    * this method creates Authorities in a more programmatic manner
    * used by : loadUserByUsername()
     */

    private String createAuthority(String methodName, String prefix) {
        return methodName + "_" + prefix;
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
                        .authorities(
                                createAuthority("CREATE", MARKET_ADMIN_PREFIX),
                                createAuthority("UPDATE", MARKET_ADMIN_PREFIX),
                                createAuthority("DELETE", MARKET_ADMIN_PREFIX),
                                createAuthority("READ", MARKET_ADMIN_PREFIX),
                                createAuthority("CREATE", RAYON_ADMIN_PREFIX),
                                createAuthority("UPDATE", RAYON_ADMIN_PREFIX),
                                createAuthority("DELETE", RAYON_ADMIN_PREFIX),
                                createAuthority("READ", RAYON_ADMIN_PREFIX)
                        )
                        .build();
            return userDetails;
        }else if(rayonAdminDTO != null) {
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(rayonAdminDTO.getEmail())
                            .password(rayonAdminDTO.getPassword())
                            .roles("RAYON_ADMIN")
                            .build();

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
