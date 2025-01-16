package com.elearn.app.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.elearn.app.dtos.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//for authentication custom exception handling
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, 
                        HttpServletResponse response,
                        AuthenticationException authException) 
            throws IOException, ServletException {
        
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            //by default it will send string in response
            //to send json, we have to create json and write json
            CustomMessage customMessage = new CustomMessage();

            customMessage.setSuccess(false);

            if(authException instanceof BadCredentialsException){
                customMessage.setMessage("Invalid username or password");
            }else{
                customMessage.setMessage("Unauthorized! Please check your credentials and try again..");
            }
            
            //manually write json to the response object
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(customMessage);
            
            PrintWriter writer = response.getWriter();
            writer.println(jsonString);

            //to see the logs in console
            //authException.printStackTrace();

    }

}
