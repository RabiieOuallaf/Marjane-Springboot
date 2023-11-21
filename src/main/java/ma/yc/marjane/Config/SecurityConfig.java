package ma.yc.marjane.Config;

import ma.yc.marjane.Services.GeneralAdminService;
import ma.yc.marjane.Services.MarketAdminService;
import ma.yc.marjane.Services.RayonAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final GeneralAdminService generalAdminService;
    private final RayonAdminService rayonAdminService;
    private final MarketAdminService marketAdminService;

    @Autowired
    public SecurityConfig(GeneralAdminService generalAdminService, RayonAdminService rayonAdminService, MarketAdminService marketAdminService) {
        this.generalAdminService = generalAdminService;
        this.rayonAdminService = rayonAdminService;
        this.marketAdminService = marketAdminService;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService()
    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v1/general-admin/**").authenticated()
//                .antMatchers("/api/v1/market-admin/**").hasAuthority("ADMIN_GENERAL")
//                .antMatchers("/api/v1/rayon-admin/**").hasAuthority("ADMIN_GENERAL")
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/api/v1/authenticate")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .permitAll();
//
//    }

}
