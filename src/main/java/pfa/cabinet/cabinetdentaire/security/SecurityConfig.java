package pfa.cabinet.cabinetdentaire.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pfa.cabinet.cabinetdentaire.entities.Secretary;
import pfa.cabinet.cabinetdentaire.repository.SecretaryRepository;

import java.util.List;

@Configuration
@EnableWebSecurity@AllArgsConstructor
public class SecurityConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("secretary").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.formLogin(form ->form.loginPage("/login").defaultSuccessUrl("/").permitAll());

        httpSecurity.rememberMe(Customizer.withDefaults());
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        return httpSecurity.build();
    }
}
