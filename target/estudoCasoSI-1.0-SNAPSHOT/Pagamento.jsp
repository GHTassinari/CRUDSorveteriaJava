<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Pagamentos</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Pagamentos</h1>
            <form name="pagamentoForm" id="pagamentoForm" action="${pageContext.request.contextPath}/controlador/PagamentoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idPagamento" value="${idPagamento}" />
                <p><label>Valor:</label> <input type="text" name="valorPagamento" value="${valorPagamento}" size="20" /> </p>
                <p><label>Método:</label> <input type="text" name="metodoPagamento" value="${metodoPagamento}" size="20" /> </p>
                <p><label>Data de Pagamento:</label> <input type="text" name="dataPagamento" value="${dataPagamento}" size="20" /> </p>
                <p><label>Tipo:</label> <input type="text" name="tipoPagamento" value="${tipoPagamento}" size="20" /> </p>
                <p><label>Descrição:</label> <input type="text" name="descricaoPagamento" value="${descricaoPagamento}" size="40" /> </p>
                <input type="submit" value="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Pagamentos</h2>
            
            <table border="1">
                <c:if test="${not empty pagamentos}">
                    <tr>
                        <th>Valor</th>
                        <th>Método</th>
                        <th>Data de Pagamento</th>
                        <th>Tipo</th>
                        <th>Descrição</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                    <c:forEach var="pagamento" items="${pagamentos}">
                        <tr>
                            <td>${pagamento.valor}</td>
                            <td>${pagamento.metodo}</td>
                            <td>${pagamento.dataPagamento}</td>
                            <td>${pagamento.tipo}</td>
                            <td>${pagamento.descricao}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controlador/PagamentoControlador" method="get">
                                    <input type="hidden" name="idPagamento" value="${pagamento.id}" />
                                    <input type="hidden" name="valorPagamento" value="${pagamento.valor}" />
                                    <input type="hidden" name="metodoPagamento" value="${pagamento.metodo}" />
                                    <input type="hidden" name="dataPagamento" value="${pagamento.dataPagamento}" />
                                    <input type="hidden" name="tipoPagamento" value="${pagamento.tipo}" />
                                    <input type="hidden" name="descricaoPagamento" value="${pagamento.descricao}" />
                                    <input type="hidden" name="opcao" value="editar" />
                                    <button type="submit" class="btn-edit">Editar</button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controlador/PagamentoControlador" method="get">
                                    <input type="hidden" name="idPagamento" value="${pagamento.id}" />
                                    <input type="hidden" name="valorPagamento" value="${pagamento.valor}" />
                                    <input type="hidden" name="metodoPagamento" value="${pagamento.metodo}" />
                                    <input type="hidden" name="dataPagamento" value="${pagamento.dataPagamento}" />
                                    <input type="hidden" name="tipoPagamento" value="${pagamento.tipo}" />
                                    <input type="hidden" name="descricaoPagamento" value="${pagamento.descricao}" />
                                    <input type="hidden" name="opcao" value="excluir" />
                                    <button type="submit" class="btn-delete">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </body>
</html>
