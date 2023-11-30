package ma.yc.marjane.Services;

import lombok.RequiredArgsConstructor;
import ma.yc.marjane.DTO.CashierAgentDTO;
import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Models.MarketAdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private final GeneralAdminService generalAdminService;
    private final MarketAdminService marketAdminService;
    private final RayonAdminService rayonAdminService;
    private final CashierAgentService cashierAgentService;

    private final String MARKET_ADMIN_PREFIX = "MARKET_ADMIN";
    private final String RAYON_ADMIN_PREFIX = "RAYON_ADMIN";
    private final String PROMOTION_PREFIX = "PROMOTION";
    private final String PRODUCT_PREFIX = "PRODUCT";
    private final String CATEGORY_PREFIX = "CATEGORY";
    private final String CLIENT_PREFIX = "CLIENT";

    private final String CASHIER_AGENT = "CASHIER_AGENT";




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
        MarketAdminModel marketAdminModel = marketAdminService.read(email);
        CashierAgentDTO cashierAgent = cashierAgentService.read(email);

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
                            .authorities(
                                    createAuthority("ACCEPT", PROMOTION_PREFIX),
                                    createAuthority("REJECT", PROMOTION_PREFIX),
                                    createAuthority("READ", PROMOTION_PREFIX),
                                    createAuthority("CREATE", CATEGORY_PREFIX),
                                    createAuthority("READ",  CATEGORY_PREFIX),
                                    createAuthority("UPDATE", CATEGORY_PREFIX),
                                    createAuthority("DELETE", CATEGORY_PREFIX),
                                    createAuthority("CREATE", CASHIER_AGENT),
                                    createAuthority("READ",  CASHIER_AGENT),
                                    createAuthority("UPDATE", CASHIER_AGENT),
                                    createAuthority("DELETE", CASHIER_AGENT)
                            )
                            .build();

            return userDetails;

        }else if(marketAdminModel != null) {
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(marketAdminModel.getEmail())
                            .password(marketAdminModel.getPassword())
                            .roles("MARKET_ADMIN")
                            .authorities(
                                    createAuthority("CREATE", PROMOTION_PREFIX),
                                    createAuthority("READ", PROMOTION_PREFIX),
                                    createAuthority("CREATE", PRODUCT_PREFIX),
                                    createAuthority("READ", PRODUCT_PREFIX),
                                    createAuthority("UPDATE", PRODUCT_PREFIX),
                                    createAuthority("DELETE", PRODUCT_PREFIX),
                                    createAuthority("CREATE", CASHIER_AGENT),
                                    createAuthority("READ", CASHIER_AGENT),
                                    createAuthority("UPDATE", CASHIER_AGENT),
                                    createAuthority("DELETE", CASHIER_AGENT)

                            )
                            .build();
            return userDetails;
        }else if(cashierAgent != null) {
            UserDetails userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(cashierAgent.getEmail())
                            .password(cashierAgent.getPassword())
                            .roles("CASHIER_AGENT")
                            .authorities(
                                    createAuthority("CREATE", CLIENT_PREFIX),
                                    createAuthority("READ", CLIENT_PREFIX),
                                    createAuthority("UPDATE", CLIENT_PREFIX),
                                    createAuthority("DELETE", CLIENT_PREFIX)

                            )
                            .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found with email" + email);
        }
    }

}
