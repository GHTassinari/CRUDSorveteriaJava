/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.UsuarioDao;
import com.mycompany.estudocasosi.modelo.entidade.Usuario;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstantes.BASE_PATH + "/LoginControlador")

public class LoginControlador extends HttpServlet {

    private UsuarioDao usuarioDAO;
    String opcao = "";
    String username = "";
    String password = "";
    String email = "";
    Usuario usuario;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDao();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            username = request.getParameter("username");
            password = request.getParameter("password");
            email = request.getParameter("email");

            usuario = usuarioDAO.getUserByUsername(username);

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida" + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Chamando o método login...");
        if (usuario != null && BCrypt.checkpw(password, usuario.getPasswordHash())) {
            request.getSession().setAttribute("user", username);
            response.sendRedirect("/EstudoCasoSI2024-master/index.jsp");
        } else {
            request.setAttribute("mensagem", "Usuário ou senha inválidos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Chamando o método cadastrar...");

        if (usuarioDAO.getUserByUsername(username) != null) {
            request.setAttribute("mensagem", "Erro: Nome de usuário já cadastrado!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroUsuario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (usuarioDAO.getUserByEmail(email) != null) {
            request.setAttribute("mensagem", "Erro: E-mail já cadastrado!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroUsuario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        usuarioDAO.registrarUsuario(username, password, email);
        request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logout(request, response);

    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            request.setAttribute("mensagem", "Sessão encerrada");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);

    }
}
