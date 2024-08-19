<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Lojas</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Lojas</h1>

            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/LojaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idLoja" value="${idLoja}" />

                <p><label>Nome:</label> <input type="text" name="nomeLoja" value="${nomeLoja}" size="40" /> </p>
                <p><label>Endereço:</label> <input type="text" name="enderecoLoja" value="${enderecoLoja}" size="40" /> </p>
                <p><label>Telefone:</label> <input type="text" name="telefoneLoja" value="${telefoneLoja}" size="40" /> </p>

                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Lojas</h2>

            <table border="1">
                <c:if test="${not empty lojas}">
                    <tr>
                        <th>Nome</th>
                        <th>Endereço</th>
                        <th>Telefone</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="loja" items="${lojas}">
                    <tr>
                        <td>${loja.nome}</td>
                        <td>${loja.endereco}</td>
                        <td>${loja.telefone}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}/controlador/LojaControlador" method="get">
                                <input type="hidden" name="idLoja" value="${loja.id}" />
                                <input type="hidden" name="nomeLoja" value="${loja.nome}" />
                                <input type="hidden" name="enderecoLoja" value="${loja.endereco}" />
                                <input type="hidden" name="telefoneLoja" value="${loja.telefone}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}/controlador/LojaControlador" method="get">
                                <input type="hidden" name="idLoja" value="${loja.id}" />
                                <input type="hidden" name="nomeLoja" value="${loja.nome}" />
                                <input type="hidden" name="enderecoLoja" value="${loja.endereco}" />
                                <input type="hidden" name="telefoneLoja" value="${loja.telefone}" />
                                <input type="hidden" name="opcao" value="excluir" />
                                <button type="submit" class="btn-delete">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
