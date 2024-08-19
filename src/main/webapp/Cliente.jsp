<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Clientes</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Cliente</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/ClienteControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idCliente" value="${idCliente}" />
                <p><label>Nome:</label> <input type="text" name="nomeCliente" value="${nomeCliente}" size="40" /> </p>
                <p><label>CPF:</label> <input type="text" name="cpfCliente" value="${cpfCliente}" size="40" /> </p>
                <p><label>Telefone:</label> <input type="text" name="telefoneCliente" value="${telefoneCliente}" size="40" /> </p>
                <p><label>Email:</label> <input type="text" name="emailCliente" value="${emailCliente}" size="40" /> </p>
                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Clientes</h2>    

            <table border="1">
                <c:if test="${not empty clientes}">
                    <tr>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Alterar</th>
                        <th>Excluir</th>         
                    </tr>
                </c:if>

                <c:forEach var="cliente" items="${clientes}">
                    <tr>
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.telefone}</td>
                        <td>${cliente.email}</td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/ClienteControlador" method="get">
                                <input type="hidden" name="idCliente" value="${cliente.id}">
                                <input type="hidden" name="nomeCliente" value="${cliente.nome}">
                                <input type="hidden" name="cpfCliente" value="${cliente.cpf}">
                                <input type="hidden" name="telefoneCliente" value="${cliente.telefone}">
                                <input type="hidden" name="emailCliente" value="${cliente.email}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/ClienteControlador" method="get">
                                <input type="hidden" name="idCliente" value="${cliente.id}">
                                <input type="hidden" name="nomeCliente" value="${cliente.nome}">
                                <input type="hidden" name="cpfCliente" value="${cliente.cpf}">
                                <input type="hidden" name="telefoneCliente" value="${cliente.telefone}">
                                <input type="hidden" name="emailCliente" value="${cliente.email}">
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
