package com.elearn.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Streaming App Backend",
        description = "Created by Vinay Yadav",
        version = "1.0V",
        contact = @Contact(
            name = "Vinay Yadav",
            email = "viniyadav@gmail.com",
            url = "https://streamingapp.com"
        ),
        license = @License(
            url = "https://streamingapp.com",
            name = "Apache license 2.0"
        )
    ),
    //to apply security to all the controllers
    security = @SecurityRequirement(name = "jwtScheme")
)
@SecurityScheme(
    name = "jwtScheme",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class ProjectConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

}
