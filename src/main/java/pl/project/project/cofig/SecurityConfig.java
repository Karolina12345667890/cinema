package pl.project.project.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * klasa konfigurująca pierwszy panel logowania
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    public AccessDeniedHandler createAccessDeniedHandler(){
        AccessDeniedHandlerImpl impl = new AccessDeniedHandlerImpl();
        impl.setErrorPage("/error403");//url
        return impl;
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/repertuar","/registrationFrom","/admin/movieList","/admin/priceList",
                        "/seatForm","/registrationSuccess","/admin/movieDetails","/history").permitAll() //do tych zasobów dostęp ma mieć każdy
                .antMatchers("/admin/**").hasRole("ADMIN") // do tych zasobów dostęp ma tylko admin
                .antMatchers("/reservedSeatForm").hasRole("USER")
                .anyRequest()
                .authenticated();
        http
                .formLogin() //pozwól użytkownikom się uwierzytelnić przez formularz
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

    }

}
