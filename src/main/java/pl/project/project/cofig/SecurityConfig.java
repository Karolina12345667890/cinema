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
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//    }
//    @Bean
//    @Profile(ProfileNames.INMEMORY)
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//
//        User.UserBuilder userBuilder = User.builder();
//
//        UserDetails user = userBuilder
//                .username("user1")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = userBuilder
//                .username("admin1")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails test = userBuilder
//                .username("useradmin1")
//                .password(passwordEncoder.encode("useradmin"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        manager.createUser(admin);
//        manager.createUser(test);
//
//        return manager;
//    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/repertuar","/registrationFrom","/index","/admin/priceList","/seatForm","/registrationSuccess","/roomForm").permitAll() //do tych zasobów dostęp ma mieć każdy
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


    }
}
