package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.LojaDao;
import com.mycompany.estudocasosi.modelo.entidade.Loja;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/LojaControlador")
public class LojaControlador extends HttpServlet {

    private LojaDao lojaDao;
    private Loja loja;
    private String nomeLoja = "";
    private String enderecoLoja = "";
    private String telefoneLoja = "";
    private String idLoja = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        lojaDao = new LojaDao();
        loja = new Loja();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idLoja = request.getParameter("idLoja");
            nomeLoja = request.getParameter("nomeLoja");
            enderecoLoja = request.getParameter("enderecoLoja");
            telefoneLoja = request.getParameter("telefoneLoja");

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
        loja.setNome(nomeLoja);
        loja.setEndereco(enderecoLoja);
        loja.setTelefone(telefoneLoja);
        lojaDao.salvar(loja);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idLoja", idLoja);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeLoja", nomeLoja);
        request.setAttribute("enderecoLoja", enderecoLoja);
        request.setAttribute("telefoneLoja", telefoneLoja);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idLoja", idLoja);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeLoja", nomeLoja);
        request.setAttribute("enderecoLoja", enderecoLoja);
        request.setAttribute("telefoneLoja", telefoneLoja);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        loja.setId(Long.valueOf(idLoja));
        loja.setNome(nomeLoja);
        loja.setEndereco(enderecoLoja);
        loja.setTelefone(telefoneLoja);
        lojaDao.alterar(loja);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loja.setId(Long.valueOf(idLoja));
        loja.setNome(nomeLoja);
        loja.setEndereco(enderecoLoja);
        loja.setTelefone(telefoneLoja);
        lojaDao.excluir(loja);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeLoja", "");
        request.setAttribute("enderecoLoja", "");
        request.setAttribute("telefoneLoja", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Loja> lojas = lojaDao.buscarTodos();
        request.setAttribute("lojas", lojas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Loja.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nomeLoja == null || nomeLoja.isEmpty()
                || enderecoLoja == null || enderecoLoja.isEmpty()
                || telefoneLoja == null || telefoneLoja.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
