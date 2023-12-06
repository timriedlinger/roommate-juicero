package de.hhu.propra.rover.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import javax.servlet.http.HttpServletResponse;

//@Configuration
public class NewConfig {



    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
        chainBuilder
                .authorizeHttpRequests(c-> c.antMatchers("/", "/error", "/css/*", "/img/*").permitAll()
                                .anyRequest().authenticated())
                .logout(c -> c.logoutSuccessUrl("/"))
                .oauth2Login(Customizer.withDefaults());
        return chainBuilder.build();
    }

}
