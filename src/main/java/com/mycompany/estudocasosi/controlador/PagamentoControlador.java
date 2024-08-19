package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.PagamentoDao;
import com.mycompany.estudocasosi.modelo.entidade.Pagamento;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/PagamentoControlador")
public class PagamentoControlador extends HttpServlet {

    private PagamentoDao pagamentoDao;
    private String idPagamento = "";
    private String valorPagamento = "";
    private String metodoPagamento = "";
    private String dataPagamento = "";
    private String tipoPagamento = "";
    private String descricaoPagamento = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        pagamentoDao = new PagamentoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idPagamento = request.getParameter("idPagamento");
            valorPagamento = request.getParameter("valorPagamento");
            metodoPagamento = request.getParameter("metodoPagamento");
            dataPagamento = request.getParameter("dataPagamento");
            tipoPagamento = request.getParameter("tipoPagamento");
            descricaoPagamento = request.getParameter("descricaoPagamento");

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
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(Double.valueOf(valorPagamento));
        pagamento.setMetodo(metodoPagamento);
        pagamento.setDataPagamento(Timestamp.valueOf(dataPagamento));
        pagamento.setTipo(tipoPagamento);
        pagamento.setDescricao(descricaoPagamento);
        pagamentoDao.salvar(pagamento);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPagamento", idPagamento);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("valorPagamento", valorPagamento);
        request.setAttribute("metodoPagamento", metodoPagamento);
        request.setAttribute("dataPagamento", dataPagamento);
        request.setAttribute("tipoPagamento", tipoPagamento);
        request.setAttribute("descricaoPagamento", descricaoPagamento);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPagamento", idPagamento);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("valorPagamento", valorPagamento);
        request.setAttribute("metodoPagamento", metodoPagamento);
        request.setAttribute("dataPagamento", dataPagamento);
        request.setAttribute("tipoPagamento", tipoPagamento);
        request.setAttribute("descricaoPagamento", descricaoPagamento);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        Pagamento pagamento = new Pagamento();
        pagamento.setId(Long.valueOf(idPagamento));
        pagamento.setValor(Double.valueOf(valorPagamento));
        pagamento.setMetodo(metodoPagamento);
        pagamento.setDataPagamento(Timestamp.valueOf(dataPagamento));
        pagamento.setTipo(tipoPagamento);
        pagamento.setDescricao(descricaoPagamento);
        pagamentoDao.alterar(pagamento);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(Long.valueOf(idPagamento));
        pagamentoDao.excluir(pagamento);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("valorPagamento", "");
        request.setAttribute("metodoPagamento", "");
        request.setAttribute("dataPagamento", "");
        request.setAttribute("tipoPagamento", "");
        request.setAttribute("descricaoPagamento", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pagamento> pagamentos = pagamentoDao.buscarTodos();
        request.setAttribute("pagamentos", pagamentos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pagamento.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (valorPagamento == null || valorPagamento.isEmpty()
                || metodoPagamento == null || metodoPagamento.isEmpty()
                || dataPagamento == null || dataPagamento.isEmpty()
                || tipoPagamento == null || tipoPagamento.isEmpty()
                || descricaoPagamento == null || descricaoPagamento.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
