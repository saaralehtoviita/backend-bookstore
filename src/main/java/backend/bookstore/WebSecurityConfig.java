package backend.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            authorize -> authorize
            .requestMatchers("/books").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/h2-console/").permitAll()
            // .requestMatchers(toH2Console()).permitAll()
            .anyRequest().authenticated())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                .disable())) //h2-konsoli ei toimi ilman tätä
            .formLogin(formlogin -> formlogin //springin default-kirjautumissivu
                .defaultSuccessUrl("/index", true) //mihin tullaan onnistuneen kirjautumisen jälkeen
                .permitAll())
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());

        return http.build();

    }

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
