package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.ClienteDao;
import com.mycompany.estudocasosi.modelo.entidade.Cliente;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private ClienteDao clienteDao;
    private Cliente cliente;
    private String nomeCliente = "";
    private String cpfCliente = "";
    private String telefoneCliente = "";
    private String emailCliente = "";
    private String idCliente = "";
    private String opcao = null;

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        cliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idCliente = request.getParameter("idCliente");
            nomeCliente = request.getParameter("nomeCliente");
            cpfCliente = request.getParameter("cpfCliente");
            telefoneCliente = request.getParameter("telefoneCliente");
            emailCliente = request.getParameter("emailCliente");

            if (opcao == null) {
                encaminharParaPagina(request, response);
                return;
            }
            if(opcao.isEmpty()){
               opcao = "cadastrar";
            }

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setNome(nomeCliente);
        cliente.setCpf(cpfCliente);
        cliente.setTelefone(telefoneCliente);
        cliente.setEmail(emailCliente);
        clienteDao.salvar(cliente);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("cpfCliente", cpfCliente);
        request.setAttribute("telefoneCliente", telefoneCliente);
        request.setAttribute("emailCliente", emailCliente);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("cpfCliente", cpfCliente);
        request.setAttribute("telefoneCliente", telefoneCliente);
        request.setAttribute("emailCliente", emailCliente);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setId(Long.valueOf(idCliente));
        cliente.setNome(nomeCliente);
        cliente.setCpf(cpfCliente);
        cliente.setTelefone(telefoneCliente);
        cliente.setEmail(emailCliente);
        clienteDao.alterar(cliente);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente.setId(Long.valueOf(idCliente));
        cliente.setNome(nomeCliente);
        cliente.setCpf(cpfCliente);
        cliente.setTelefone(telefoneCliente);
        cliente.setEmail(emailCliente);
        clienteDao.excluir(cliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeCliente", "");
        request.setAttribute("cpfCliente", "");
        request.setAttribute("telefoneCliente", "");
        request.setAttribute("emailCliente", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.buscarTodos();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nomeCliente == null || nomeCliente.isEmpty()
                || cpfCliente == null || cpfCliente.isEmpty()
                || telefoneCliente == null || telefoneCliente.isEmpty()
                || emailCliente == null || emailCliente.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
