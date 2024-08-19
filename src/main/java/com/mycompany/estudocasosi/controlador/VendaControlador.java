package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.VendaDao;
import com.mycompany.estudocasosi.modelo.entidade.Venda;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/VendaControlador")
public class VendaControlador extends HttpServlet {

    private VendaDao vendaDao;

    @Override
    public void init() throws ServletException {
        vendaDao = new VendaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null) {
                encaminharParaPagina(request, response);
                return;
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
        Venda venda = preencherVenda(request);
        vendaDao.salvar(venda);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Venda venda = vendaDao.buscarPorId(id);
        
        request.setAttribute("venda", venda);
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Venda venda = preencherVenda(request);
        vendaDao.alterar(venda);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Venda venda = new Venda();
        venda.setId(id);
        vendaDao.excluir(venda);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clientes", vendaDao.buscarTodosClientes());
        request.setAttribute("funcionarios", vendaDao.buscarTodosFuncionarios());
        request.setAttribute("fornecedores", vendaDao.buscarTodosFornecedores());
        request.setAttribute("sorvetes", vendaDao.buscarTodosSorvetes());
        request.setAttribute("maquinasDeSorvete", vendaDao.buscarTodasMaquinasDeSorvete());
        request.setAttribute("pagamentos", vendaDao.buscarTodosPagamentos());
        request.setAttribute("lojas", vendaDao.buscarTodasLojas());

        List<Venda> vendas = vendaDao.buscarTodos();
        request.setAttribute("vendas", vendas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Venda.jsp");
        dispatcher.forward(request, response);
    }

    private Venda preencherVenda(HttpServletRequest request) {
        Venda venda = new Venda();

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                venda.setId(Long.valueOf(idParam));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("O ID deve ser um número válido.");
            }
        }

        try {
            venda.setIdCliente(Long.valueOf(request.getParameter("idCliente")));
            venda.setIdFuncionario(Long.valueOf(request.getParameter("idFuncionario")));
            venda.setIdFornecedor(Long.valueOf(request.getParameter("idFornecedor")));
            venda.setIdSorvete(Long.valueOf(request.getParameter("idSorvete")));
            venda.setIdMaquinaDeSorvete(Long.valueOf(request.getParameter("idMaquinaDeSorvete")));
            venda.setIdPagamento(Long.valueOf(request.getParameter("idPagamento")));
            venda.setIdLoja(Long.valueOf(request.getParameter("idLoja")));
            venda.setDataVenda(new Timestamp(new Date().getTime()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Um ou mais parâmetros não são números válidos.");
        }

        return venda;
    }
}
