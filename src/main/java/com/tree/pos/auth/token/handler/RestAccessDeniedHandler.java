package com.tree.pos.auth.token.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
                Map<String, Object> mapResponse= new HashMap<>();
                mapResponse.put("status", "401");
                mapResponse.put("message","unauthorized access");
                
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                OutputStream out = response.getOutputStream();
                ObjectMapper mapper =  new ObjectMapper();
                mapper.writerWithDefaultPrettyPrinter().writeValue(out,mapResponse);
                out.flush();
    }
    
}