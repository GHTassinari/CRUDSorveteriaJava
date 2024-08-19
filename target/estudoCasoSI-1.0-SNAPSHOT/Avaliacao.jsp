<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Avaliação</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Avaliação</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/AvaliacaoControlador" method="get">
                <input type="hidden" name="id" value="${avaliacao != null ? avaliacao.id : ''}" />
                <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}" />

                <!-- Seção de Venda -->
                <p><label>Venda:</label>
                    <select name="vendaId">
                        <option value="" disabled selected>Selecione uma Venda</option>
                        <c:forEach var="venda" items="${vendas}">
                            <option value="${venda.id}" <c:if test="${avaliacao != null && avaliacao.vendaId == venda.id}">selected</c:if>>${venda.dataVenda}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Descrição -->
                <p><label>Descrição:</label>
                    <textarea name="descricao">${avaliacao != null ? avaliacao.descricao : ''}</textarea>
                </p>

                <!-- Seção de Nota -->
                <p><label>Nota:</label>
                    <input type="number" name="nota" value="${avaliacao != null ? avaliacao.nota : ''}" min="0" max="10" />
                </p>

                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Avaliações</h2>    

            <table border="1">
                <c:if test="${not empty avaliacoes}">
                    <tr>
                        <th>Data da Venda</th>
                        <th>Descrição</th>
                        <th>Nota</th>
                        <th>Alterar</th>
                        <th>Excluir</th>         
                    </tr>
                </c:if>

                <c:forEach var="avaliacao" items="${avaliacoes}">
                    <tr>
                        <td>${avaliacao.venda.dataVenda}</td>
                        <td>${avaliacao.descricao}</td>
                        <td>${avaliacao.nota}</td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/AvaliacaoControlador" method="get">
                                <input type="hidden" name="id" value="${avaliacao.id}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/AvaliacaoControlador" method="get">
                                <input type="hidden" name="id" value="${avaliacao.id}">
                                <input type="hidden" name="opcao" value="confirmarExcluir">
                                <button type="submit" class="btn-delete">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
