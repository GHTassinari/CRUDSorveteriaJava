package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.AvaliacaoDao;
import com.mycompany.estudocasosi.modelo.dao.VendaDao;
import com.mycompany.estudocasosi.modelo.entidade.Avaliacao;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/AvaliacaoControlador")
public class AvaliacaoControlador extends HttpServlet {

    private AvaliacaoDao avaliacaoDao;
    private VendaDao vendaDao;

    @Override
    public void init() throws ServletException {
        avaliacaoDao = new AvaliacaoDao();
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
        Avaliacao avaliacao = preencherAvaliacao(request);
        avaliacaoDao.salvar(avaliacao);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Avaliacao avaliacao = avaliacaoDao.buscarPorId(id);

        request.setAttribute("avaliacao", avaliacao);
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Avaliacao avaliacao = preencherAvaliacao(request);
        avaliacaoDao.alterar(avaliacao);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        avaliacaoDao.excluir(avaliacao);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("vendas", vendaDao.buscarTodos());
        List<Avaliacao> avaliacoes = avaliacaoDao.buscarTodos();
        request.setAttribute("avaliacoes", avaliacoes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Avaliacao.jsp");
        dispatcher.forward(request, response);
    }

    private Avaliacao preencherAvaliacao(HttpServletRequest request) {
        Avaliacao avaliacao = new Avaliacao();

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                avaliacao.setId(Long.valueOf(idParam));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("O ID deve ser um número válido.");
            }
        }

        try {
            avaliacao.setVendaId(Long.valueOf(request.getParameter("vendaId")));
            avaliacao.setDescricao(request.getParameter("descricao"));
            avaliacao.setNota(Integer.parseInt(request.getParameter("nota")));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Um ou mais parâmetros não são válidos.");
        }

        return avaliacao;
    }
}
