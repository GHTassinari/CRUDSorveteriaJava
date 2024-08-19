package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.MaquinaDeSorveteDao;
import com.mycompany.estudocasosi.modelo.entidade.MaquinaDeSorvete;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/MaquinaDeSorveteControlador")
public class MaquinaDeSorveteControlador extends HttpServlet {

    private MaquinaDeSorveteDao maquinaDeSorveteDao;
    private MaquinaDeSorvete maquinaDeSorvete;
    private String modeloMaquina = "";
    private String capacidadeMaquina = "";
    private String dataAquisicaoMaquina = "";
    private String statusMaquina = "";
    private String idMaquina = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        maquinaDeSorveteDao = new MaquinaDeSorveteDao();
        maquinaDeSorvete = new MaquinaDeSorvete();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idMaquina = request.getParameter("idMaquina");
            modeloMaquina = request.getParameter("modeloMaquina");
            capacidadeMaquina = request.getParameter("capacidadeMaquina");
            dataAquisicaoMaquina = request.getParameter("dataAquisicaoMaquina");
            statusMaquina = request.getParameter("statusMaquina");

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
        maquinaDeSorvete.setModelo(modeloMaquina);
        maquinaDeSorvete.setCapacidade(Double.valueOf(capacidadeMaquina));
        maquinaDeSorvete.setDataAquisicao(Date.valueOf(dataAquisicaoMaquina));
        maquinaDeSorvete.setStatus(statusMaquina);
        maquinaDeSorveteDao.salvar(maquinaDeSorvete);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idMaquina", idMaquina);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("modeloMaquina", modeloMaquina);
        request.setAttribute("capacidadeMaquina", capacidadeMaquina);
        request.setAttribute("dataAquisicaoMaquina", dataAquisicaoMaquina);
        request.setAttribute("statusMaquina", statusMaquina);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idMaquina", idMaquina);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("modeloMaquina", modeloMaquina);
        request.setAttribute("capacidadeMaquina", capacidadeMaquina);
        request.setAttribute("dataAquisicaoMaquina", dataAquisicaoMaquina);
        request.setAttribute("statusMaquina", statusMaquina);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        maquinaDeSorvete.setId(Long.valueOf(idMaquina));
        maquinaDeSorvete.setModelo(modeloMaquina);
        maquinaDeSorvete.setCapacidade(Double.valueOf(capacidadeMaquina));
        maquinaDeSorvete.setDataAquisicao(Date.valueOf(dataAquisicaoMaquina));
        maquinaDeSorvete.setStatus(statusMaquina);
        maquinaDeSorveteDao.alterar(maquinaDeSorvete);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        maquinaDeSorvete.setId(Long.valueOf(idMaquina));
        maquinaDeSorvete.setModelo(modeloMaquina);
        maquinaDeSorvete.setCapacidade(Double.valueOf(capacidadeMaquina));
        maquinaDeSorvete.setDataAquisicao(Date.valueOf(dataAquisicaoMaquina));
        maquinaDeSorvete.setStatus(statusMaquina);
        maquinaDeSorveteDao.excluir(maquinaDeSorvete);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("modeloMaquina", "");
        request.setAttribute("capacidadeMaquina", "");
        request.setAttribute("dataAquisicaoMaquina", "");
        request.setAttribute("statusMaquina", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MaquinaDeSorvete> maquinas = maquinaDeSorveteDao.buscarTodos();
        request.setAttribute("maquinas", maquinas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MaquinaDeSorvete.jsp");
        dispatcher.forward(request, response);
    }

    private void validaCampos() {
        if (modeloMaquina == null || modeloMaquina.isEmpty()
                || capacidadeMaquina == null || capacidadeMaquina.isEmpty()
                || dataAquisicaoMaquina == null || dataAquisicaoMaquina.isEmpty()
                || statusMaquina == null || statusMaquina.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
