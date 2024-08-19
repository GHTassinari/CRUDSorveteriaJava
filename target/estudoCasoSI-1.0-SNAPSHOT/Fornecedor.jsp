<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Fornecedores</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Fornecedores</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FornecedorControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idFornecedor" value="${idFornecedor}" />
                <p><label>Nome:</label> <input type="text" name="nomeFornecedor" value="${nomeFornecedor}" size="40" /> </p>
                <p><label>Contato:</label> <input type="text" name="contatoFornecedor" value="${contatoFornecedor}" size="40" /> </p>
                <p><label>Endereço:</label> <input type="text" name="enderecoFornecedor" value="${enderecoFornecedor}" size="40" /> </p>
                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>
                
            <h2>Lista de Fornecedores</h2>

            <table border="1">
                <c:if test="${not empty fornecedores}">
                    <tr>
                        <th>Nome</th>
                        <th>Contato</th>
                        <th>Endereço</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="fornecedor" items="${fornecedores}">
                    <tr>
                        <td>${fornecedor.nome}</td>
                        <td>${fornecedor.contato}</td>
                        <td>${fornecedor.endereco}</td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FornecedorControlador" method="get">
                                <input type="hidden" name="idFornecedor" value="${fornecedor.id}">
                                <input type="hidden" name="nomeFornecedor" value="${fornecedor.nome}">
                                <input type="hidden" name="contatoFornecedor" value="${fornecedor.contato}">
                                <input type="hidden" name="enderecoFornecedor" value="${fornecedor.endereco}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/FornecedorControlador" method="get">
                                <input type="hidden" name="idFornecedor" value="${fornecedor.id}">
                                <input type="hidden" name="nomeFornecedor" value="${fornecedor.nome}">
                                <input type="hidden" name="contatoFornecedor" value="${fornecedor.contato}">
                                <input type="hidden" name="enderecoFornecedor" value="${fornecedor.endereco}">
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
