package com.elearn.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//mport org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean //we have to create bean in order to customize the security things.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //customization
        //routes/urls
        httpSecurity.authorizeHttpRequests(d -> {
            d.requestMatchers("/api/v1/categories").permitAll()
            .requestMatchers("/client-login").permitAll()
            //d.requestMatchers(HttpMethod.GET, "/api/v1/courses","/api/v1/videos/**").permitAll()
            .anyRequest().authenticated();
        });
        //basic javascript based authentication.. not form based
        httpSecurity.httpBasic(Customizer.withDefaults());
        //httpSecurity.formLogin(Customizer.withDefaults()); //default browser form based login
        //customize form based login
        httpSecurity.formLogin(form -> {
            form.loginPage("/client-login");
            form.usernameParameter("username");
            form.passwordParameter("userPassword");
            form.loginProcessingUrl("/client-login-process");
            form.successForwardUrl("/success");
            //form.successHandler(null);
            //form.failureHandler(null);
        });
        //disable cors
        httpSecurity.cors(e->e.disable());
        return httpSecurity.build();
    }

}
