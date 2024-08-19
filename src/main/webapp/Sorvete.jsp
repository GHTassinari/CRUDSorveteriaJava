<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Sorvete</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">

    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Sorvetes</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/SorveteControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idSorvete" value="${idSorvete}" />
                <p><label>Sabor:</label> <input type="text" name="saborSorvete" value="${saborSorvete}" size="40" /> </p>
                <p><label>Tamanho:</label> <input type="text" name="tamanhoSorvete" value="${tamanhoSorvete}" size="40" /> </p>
                <p><label>Preço:</label> <input type="text" name="precoSorvete" value="${precoSorvete}" size="40" /> </p>
                <p><label>Quantidade:</label> <input type="text" name="quantidadeSorvete" value="${quantidadeSorvete}" size="40" /> </p>
                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Sorvetes</h2>
            
            <table>
                <c:if test="${not empty sorvetes}">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Sabor</th>
                            <th>Tamanho</th>
                            <th>Preço</th>
                            <th>Quantidade</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sorvete" items="${sorvetes}">
                            <tr>
                                <td>${sorvete.id}</td>
                                <td>${sorvete.sabor}</td>
                                <td>${sorvete.tamanho}</td>
                                <td>${sorvete.preco}</td>
                                <td>${sorvete.quantidade}</td>
                                <td>    
                                    <form name="editarForm" action="${pageContext.request.contextPath}/controlador/SorveteControlador" method="get">
                                        <input type="hidden" name="idSorvete" value="${sorvete.id}">
                                        <input type="hidden" name="saborSorvete" value="${sorvete.sabor}">
                                        <input type="hidden" name="tamanhoSorvete" value="${sorvete.tamanho}">
                                        <input type="hidden" name="precoSorvete" value="${sorvete.preco}">
                                        <input type="hidden" name="quantidadeSorvete" value="${sorvete.quantidade}">
                                        <input type="hidden" name="opcao" value="editar">
                                        <button type="submit" class="btn-edit">Editar</button>
                                    </form>
                                </td>
                                <td>    
                                    <form name="excluirForm" action="${pageContext.request.contextPath}/controlador/SorveteControlador" method="get">
                                        <input type="hidden" name="idSorvete" value="${sorvete.id}">
                                        <input type="hidden" name="saborSorvete" value="${sorvete.sabor}">
                                        <input type="hidden" name="tamanhoSorvete" value="${sorvete.tamanho}">
                                        <input type="hidden" name="precoSorvete" value="${sorvete.preco}">
                                        <input type="hidden" name="quantidadeSorvete" value="${sorvete.quantidade}">
                                        <input type="hidden" name="opcao" value="excluir">
                                        <button type="submit" class="btn-delete">Excluir</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:if>
            </table>
        </div>
    </body>
</html>
