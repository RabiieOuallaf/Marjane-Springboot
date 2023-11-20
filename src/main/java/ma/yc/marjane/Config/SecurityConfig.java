//package ma.yc.marjane.Config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
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
//
//}
