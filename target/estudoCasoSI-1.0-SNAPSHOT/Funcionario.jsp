<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Funcionários</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Funcionários</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FuncionarioControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idFuncionario" value="${idFuncionario}" />
                <p><label>Nome:</label> <input type="text" name="nomeFuncionario" value="${nomeFuncionario}" size="40" /> </p>
                <p><label>Cargo:</label> <input type="text" name="cargoFuncionario" value="${cargoFuncionario}" size="40" /> </p>
                <p><label>Salário:</label> <input type="text" name="salarioFuncionario" value="${salarioFuncionario}" size="40" /> </p>
                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>
                
            <h2>Lista de Funcionários</h2>

            <table border="1">
                <c:if test="${not empty funcionarios}">
                    <tr>
                        <th>Nome</th>
                        <th>Cargo</th>
                        <th>Salário</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="funcionario" items="${funcionarios}">
                    <tr>
                        <td>${funcionario.nome}</td>
                        <td>${funcionario.cargo}</td>
                        <td>${funcionario.salario}</td>
                        <td>
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FuncionarioControlador" method="get">
                                <input type="hidden" name="idFuncionario" value="${funcionario.id}">
                                <input type="hidden" name="nomeFuncionario" value="${funcionario.nome}">
                                <input type="hidden" name="cargoFuncionario" value="${funcionario.cargo}">
                                <input type="hidden" name="salarioFuncionario" value="${funcionario.salario}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FuncionarioControlador" method="get">
                                <input type="hidden" name="idFuncionario" value="${funcionario.id}">
                                <input type="hidden" name="nomeFuncionario" value="${funcionario.nome}">
                                <input type="hidden" name="cargoFuncionario" value="${funcionario.cargo}">
                                <input type="hidden" name="salarioFuncionario" value="${funcionario.salario}">
                                <input type="hidden" name="opcao" value="excluir">
                                <button type="submit" class="btn-delete">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
