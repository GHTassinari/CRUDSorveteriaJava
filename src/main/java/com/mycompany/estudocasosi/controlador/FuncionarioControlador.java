package com.mycompany.estudocasosi.controlador;

import com.mycompany.estudocasosi.modelo.dao.FuncionarioDao;
import com.mycompany.estudocasosi.modelo.entidade.Funcionario;
import com.mycompany.estudocasosi.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionario;
    private String nomeFuncionario = "";
    private String cargoFuncionario = "";
    private String salarioFuncionario = "";
    private String idFuncionario = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        funcionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idFuncionario = request.getParameter("idFuncionario");
            nomeFuncionario = request.getParameter("nomeFuncionario");
            cargoFuncionario = request.getParameter("cargoFuncionario");
            salarioFuncionario = request.getParameter("salarioFuncionario");

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
        funcionario.setNome(nomeFuncionario);
        funcionario.setCargo(cargoFuncionario);
        funcionario.setSalario(Double.valueOf(salarioFuncionario));
        funcionarioDao.salvar(funcionario);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncionario", idFuncionario);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cargoFuncionario", cargoFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncionario", idFuncionario);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cargoFuncionario", cargoFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        funcionario.setId(Long.valueOf(idFuncionario));
        funcionario.setNome(nomeFuncionario);
        funcionario.setCargo(cargoFuncionario);
        funcionario.setSalario(Double.valueOf(salarioFuncionario));
        funcionarioDao.alterar(funcionario);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        funcionario.setId(Long.valueOf(idFuncionario));
        funcionario.setNome(nomeFuncionario);
        funcionario.setCargo(cargoFuncionario);
        funcionario.setSalario(Double.valueOf(salarioFuncionario));
        funcionarioDao.excluir(funcionario);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeFuncionario", "");
        request.setAttribute("cargoFuncionario", "");
        request.setAttribute("salarioFuncionario", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionarios = funcionarioDao.buscarTodos();
        request.setAttribute("funcionarios", funcionarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Funcionario.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nomeFuncionario == null || nomeFuncionario.isEmpty()
                || cargoFuncionario == null || cargoFuncionario.isEmpty()
                || salarioFuncionario == null || salarioFuncionario.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
