package com.tree.pos.auth.token;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
    
                Map<String, Object> mapResponse= new HashMap<>();
                mapResponse.put("eror_code", 401);
                mapResponse.put("messages", "Wrong Email or Password ");
                mapResponse.put("data_error", authException.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                OutputStream out = response.getOutputStream();
                ObjectMapper mapper =  new ObjectMapper();
                mapper.writer().writeValue(out,mapResponse);
                out.flush();        
    }
    
   
}