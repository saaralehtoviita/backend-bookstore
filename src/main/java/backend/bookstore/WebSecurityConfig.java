package backend.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//tässä luokassa määritellään URL-tasoiset security oikeudet
@Configuration
//metoditasoiset security-konfiguaariot annotaatio 
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {


    //kun luodaan uusi käyttäjä, tehdään salasanasta kryptattu versio
    @Bean 
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            authorize -> authorize
            .requestMatchers("/css/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/books**").permitAll()
            .requestMatchers(HttpMethod.GET, "/categories").permitAll()
            .requestMatchers(HttpMethod.POST, "/books**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/books**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/books**").hasAuthority("ADMIN")  
            .requestMatchers("/h2-console/").permitAll()
            // .requestMatchers(toH2Console()).permitAll()
            .anyRequest().authenticated()) //tekee postmaniin login-toiminnon
            .httpBasic(Customizer.withDefaults())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                .disable())) //h2-konsoli ei toimi ilman tätä
            .formLogin(formlogin -> formlogin //springin default-kirjautumissivu
                .defaultSuccessUrl("/index", true) //mihin tullaan onnistuneen kirjautumisen jälkeen
                .permitAll())
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());

        return http.build();

    }

    /*     private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    } */

/*     @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
 */
}
