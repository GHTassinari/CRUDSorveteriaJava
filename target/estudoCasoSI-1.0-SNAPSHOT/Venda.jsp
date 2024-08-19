<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Vendas</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Venda</h1>
            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/VendaControlador" method="get">
                <input type="hidden" name="id" value="${venda != null ? venda.id : ''}" />
                <input type="hidden" name="opcao" value="${opcao != null ? opcao : 'cadastrar'}" />

                <!-- Seção de Cliente -->
                <p><label>Cliente:</label>
                    <select name="idCliente">
                        <option value="" disabled selected>Selecione um Cliente</option>
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.id}" <c:if test="${venda != null && venda.idCliente == cliente.id}">selected</c:if>>${cliente.nome}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Funcionário -->
                <p><label>Funcionário:</label>
                    <select name="idFuncionario">
                        <option value="" disabled selected>Selecione um Funcionário</option>
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <option value="${funcionario.id}" <c:if test="${venda != null && venda.idFuncionario == funcionario.id}">selected</c:if>>${funcionario.nome}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Fornecedor -->
                <p><label>Fornecedor:</label>
                    <select name="idFornecedor">
                        <option value="" disabled selected>Selecione um Fornecedor</option>
                        <c:forEach var="fornecedor" items="${fornecedores}">
                            <option value="${fornecedor.id}" <c:if test="${venda != null && venda.idFornecedor == fornecedor.id}">selected</c:if>>${fornecedor.nome}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Sorvete -->
                <p><label>Sabor do sorvete:</label>
                    <select name="idSorvete">
                        <option value="" disabled selected>Selecione um Sabor</option>
                        <c:forEach var="sorvete" items="${sorvetes}">
                            <option value="${sorvete.id}" <c:if test="${venda != null && venda.idSorvete == sorvete.id}">selected</c:if>>${sorvete.sabor}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Máquina de Sorvete -->
                <p><label>Máquina de Sorvete:</label>
                    <select name="idMaquinaDeSorvete">
                        <option value="" disabled selected>Selecione uma Máquina</option>
                        <c:forEach var="maquinaDeSorvete" items="${maquinasDeSorvete}">
                            <option value="${maquinaDeSorvete.id}" <c:if test="${venda != null && venda.idMaquinaDeSorvete == maquinaDeSorvete.id}">selected</c:if>>${maquinaDeSorvete.modelo}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Pagamento -->
                <p><label>Valor do pagamento:</label>
                    <select name="idPagamento">
                        <option value="" disabled selected>Selecione um Valor</option>
                        <c:forEach var="pagamento" items="${pagamentos}">
                            <option value="${pagamento.id}" <c:if test="${venda != null && venda.idPagamento == pagamento.id}">selected</c:if>>${pagamento.valor}</option>
                        </c:forEach>
                    </select>
                </p>

                <!-- Seção de Loja -->
                <p><label>Loja:</label>
                    <select name="idLoja">
                        <option value="" disabled selected>Selecione uma Loja</option>
                        <c:forEach var="loja" items="${lojas}">
                            <option value="${loja.id}" <c:if test="${venda != null && venda.idLoja == loja.id}">selected</c:if>>${loja.nome}</option>
                        </c:forEach>
                    </select>
                </p>

                <input type="submit" value="Salvar" name="Salvar" />
                <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
            </form>

            <h2>Lista de Vendas</h2>    

            <table border="1">
                <c:if test="${not empty vendas}">
                    <tr>
                        <th>Cliente</th>
                        <th>Funcionário</th>
                        <th>Fornecedor</th>
                        <th>Sorvete</th>
                        <th>Máquina de Sorvete</th>
                        <th>Pagamento</th>
                        <th>Loja</th>
                        <th>Data Venda</th>
                        <th>Alterar</th>
                        <th>Excluir</th>         
                    </tr>
                </c:if>

                <c:forEach var="venda" items="${vendas}">
                    <tr>
                        <td>${venda.cliente.nome}</td>
                        <td>${venda.funcionario.nome}</td>
                        <td>${venda.fornecedor.nome}</td>
                        <td>${venda.sorvete.sabor}</td>
                        <td>${venda.maquinaDeSorvete.modelo}</td>
                        <td>${venda.pagamento.valor}</td>
                        <td>${venda.loja.nome}</td>
                        <td>${venda.dataVenda}</td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/VendaControlador" method="get">
                                <input type="hidden" name="id" value="${venda.id}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>    
                            <form name="cadastroForm" action="${pageContext.request.contextPath}/controlador/VendaControlador" method="get">
                                <input type="hidden" name="id" value="${venda.id}">
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
