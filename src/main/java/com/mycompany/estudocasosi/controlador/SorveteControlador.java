package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.SorveteDao;
import com.mycompany.estudocasosi.modelo.entidade.Sorvete;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/SorveteControlador")
public class SorveteControlador extends HttpServlet {

    private SorveteDao sorveteDao;
    private Sorvete sorvete;
    private String saborSorvete = "";
    private String tamanhoSorvete = "";
    private String precoSorvete = "";
    private String quantidadeSorvete = "";
    private String idSorvete = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        sorveteDao = new SorveteDao();
        sorvete = new Sorvete();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idSorvete = request.getParameter("idSorvete");
            saborSorvete = request.getParameter("saborSorvete");
            tamanhoSorvete = request.getParameter("tamanhoSorvete");
            precoSorvete = request.getParameter("precoSorvete");
            quantidadeSorvete = request.getParameter("quantidadeSorvete");

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
        sorvete.setSabor(saborSorvete);
        sorvete.setTamanho(Double.parseDouble(tamanhoSorvete));
        sorvete.setPreco(Double.parseDouble(precoSorvete));
        sorvete.setQuantidade(Integer.parseInt(quantidadeSorvete));
        sorveteDao.salvar(sorvete);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idSorvete", idSorvete);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("saborSorvete", saborSorvete);
        request.setAttribute("tamanhoSorvete", tamanhoSorvete);
        request.setAttribute("precoSorvete", precoSorvete);
        request.setAttribute("quantidadeSorvete", quantidadeSorvete);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idSorvete", idSorvete);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("saborSorvete", saborSorvete);
        request.setAttribute("tamanhoSorvete", tamanhoSorvete);
        request.setAttribute("precoSorvete", precoSorvete);
        request.setAttribute("quantidadeSorvete", quantidadeSorvete);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        sorvete.setId(Long.valueOf(idSorvete));
        sorvete.setSabor(saborSorvete);
        sorvete.setTamanho(Double.parseDouble(tamanhoSorvete));
        sorvete.setPreco(Double.parseDouble(precoSorvete));
        sorvete.setQuantidade(Integer.parseInt(quantidadeSorvete));
        sorveteDao.alterar(sorvete);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sorvete.setId(Long.valueOf(idSorvete));
        sorveteDao.excluir(sorvete);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("saborSorvete", "");
        request.setAttribute("tamanhoSorvete", "");
        request.setAttribute("precoSorvete", "");
        request.setAttribute("quantidadeSorvete", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Sorvete> sorvetes = sorveteDao.buscarTodos();
        request.setAttribute("sorvetes", sorvetes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Sorvete.jsp");
        dispatcher.forward(request, response);
    }

    private void validaCampos() {
        if (saborSorvete == null || saborSorvete.isEmpty()
                || tamanhoSorvete == null || tamanhoSorvete.isEmpty()
                || precoSorvete == null || precoSorvete.isEmpty()
                || quantidadeSorvete == null || quantidadeSorvete.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
