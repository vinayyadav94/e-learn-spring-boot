package com.elearn.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//mport org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder paasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //In memory user management
    // @Bean
    // public UserDetailsService userDetailsService(){
        //inside service we will create user
        // InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        // userDetailsManager.createUser(
        //     User.withDefaultPasswordEncoder()
        //         .username("test")
        //         .password("test")
        //         .roles("ADMIN")
        //         .build()
        // );
        //second user
    //     userDetailsManager.createUser(
    //         User.withDefaultPasswordEncoder()
    //         .username("vini")
    //         .password("vini")
    //         .roles("USER")
    //         .build());
    //     return userDetailsManager;
    // }

    @Bean //we have to create bean in order to customize the security things.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //customization
        //disable cors
        httpSecurity.cors(e -> e.disable());
        httpSecurity.csrf(e -> e.disable());
        //routes/urls
        httpSecurity.authorizeHttpRequests(
            auth -> auth.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("GUEST", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                //.requestMatchers("/all").permitAll() //this will allow all request without authentication
                .anyRequest()
                .authenticated()
            // d.requestMatchers(HttpMethod.GET,"/api/v1/categories").permitAll()
            // .requestMatchers("/client-login").permitAll()
            // .requestMatchers("/api/v1/users").permitAll()
             //d.requestMatchers(HttpMethod.GET, "/api/v1/courses","/api/v1/videos/**").permitAll()
            // .anyRequest().authenticated();
        );
        //basic javascript based authentication.. not form based
        httpSecurity.httpBasic(auth -> auth.authenticationEntryPoint(customAuthenticationEntryPoint));
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
        //httpSecurity.logout(logout -> logout.logoutUrl("/logout"));

        //configure custom authentication handler
        // httpSecurity.exceptionHandling(ex -> {
        //     ex.authenticationEntryPoint(customAuthenticationEntryPoint);
        // });
        
        return httpSecurity.build();
    }

}
