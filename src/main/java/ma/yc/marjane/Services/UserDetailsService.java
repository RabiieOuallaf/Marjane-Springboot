package ma.yc.marjane.Services;

import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.DTO.RayonAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        MarketAdminDTO marketAdminDTO = marketAdminService.read(email);
    }

}
