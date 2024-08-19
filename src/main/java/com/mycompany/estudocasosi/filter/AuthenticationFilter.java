package com.mycompany.estudocasosi.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpSession session = request.getSession(false);

    boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
    String requestURI = request.getRequestURI();
    
    boolean isLoginCss = requestURI.endsWith("/css/Login.module.css");
    boolean isCadastroCss = requestURI.endsWith("/css/Cadastro.module.css");
    
    boolean isLoginPage = requestURI.contains("login.jsp") || requestURI.contains("/LoginControlador");
    boolean isLogoutPage = requestURI.contains("/LogoutControlador");
    boolean isCadastroPage = requestURI.contains("CadastroUsuario.jsp") || requestURI.contains("/CadastroUsuarioControlador");

    if (isLoggedIn || isLoginPage || isLogoutPage || isCadastroPage || isLoginCss || isCadastroCss) {
        chain.doFilter(request, response);
    } else {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
}

