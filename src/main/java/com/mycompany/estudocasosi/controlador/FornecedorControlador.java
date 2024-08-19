package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.FornecedorDao;
import com.mycompany.estudocasosi.modelo.entidade.Fornecedor;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/FornecedorControlador")
public class FornecedorControlador extends HttpServlet {

    private FornecedorDao fornecedorDao;
    private Fornecedor fornecedor;
    private String nomeFornecedor = "";
    private String contatoFornecedor = "";
    private String enderecoFornecedor = "";
    private String idFornecedor = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        fornecedorDao = new FornecedorDao();
        fornecedor = new Fornecedor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idFornecedor = request.getParameter("idFornecedor");
            nomeFornecedor = request.getParameter("nomeFornecedor");
            contatoFornecedor = request.getParameter("contatoFornecedor");
            enderecoFornecedor = request.getParameter("enderecoFornecedor");

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
        fornecedor.setNome(nomeFornecedor);
        fornecedor.setContato(contatoFornecedor);
        fornecedor.setEndereco(enderecoFornecedor);
        fornecedorDao.salvar(fornecedor);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFornecedor", idFornecedor);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeFornecedor", nomeFornecedor);
        request.setAttribute("contatoFornecedor", contatoFornecedor);
        request.setAttribute("enderecoFornecedor", enderecoFornecedor);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFornecedor", idFornecedor);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeFornecedor", nomeFornecedor);
        request.setAttribute("contatoFornecedor", contatoFornecedor);
        request.setAttribute("enderecoFornecedor", enderecoFornecedor);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        fornecedor.setId(Long.valueOf(idFornecedor));
        fornecedor.setNome(nomeFornecedor);
        fornecedor.setContato(contatoFornecedor);
        fornecedor.setEndereco(enderecoFornecedor);
        fornecedorDao.alterar(fornecedor);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fornecedor.setId(Long.valueOf(idFornecedor));
        fornecedor.setNome(nomeFornecedor);
        fornecedor.setContato(contatoFornecedor);
        fornecedor.setEndereco(enderecoFornecedor);
        fornecedorDao.excluir(fornecedor);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeFornecedor", "");
        request.setAttribute("contatoFornecedor", "");
        request.setAttribute("enderecoFornecedor", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> fornecedores = fornecedorDao.buscarTodos();
        request.setAttribute("fornecedores", fornecedores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Fornecedor.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nomeFornecedor == null || nomeFornecedor.isEmpty()
                || contatoFornecedor == null || contatoFornecedor.isEmpty()
                || enderecoFornecedor == null || enderecoFornecedor.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
