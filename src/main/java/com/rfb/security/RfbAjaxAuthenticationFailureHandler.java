package com.rfb.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RfbAjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Falha no login. Verifique suas credênciais";

        if( exception.getMessage().equalsIgnoreCase("Blocked") ) {
            errorMessage = "Você está bloqueado por efetuar 3 tentativas de login erradas";
        }

        response.sendError(401, errorMessage);
    }

}
