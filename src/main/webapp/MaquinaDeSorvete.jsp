<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="menu.jsp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Máquinas de Sorvete</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Paginas.module.css">
    </head>
    <body>
        <div class="page-content">
            <h1>Cadastro de Máquinas de Sorvete</h1>

            <!-- Formulário para adicionar ou editar uma máquina de sorvete -->
            <form id="cadastroForm" action="${pageContext.request.contextPath}/controlador/MaquinaDeSorveteControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="idMaquina" value="${idMaquina}" />

                <p><label>Modelo:</label> 
                    <input type="text" name="modeloMaquina" value="${modeloMaquina}" size="40" /> </p>

                <p><label>Capacidade:</label> 
                    <input type="text" name="capacidadeMaquina" value="${capacidadeMaquina}" size="40" /> </p>

                <p><label>Data de Aquisição:</label> 
                    <input type="date" name="dataAquisicaoMaquina" value="${dataAquisicaoMaquina}" /> </p>

                <p><label>Status:</label> 
                    <input type="text" name="statusMaquina" value="${statusMaquina}" size="40" /> </p>

                <div class="inputMaquinaDeSorveteWrapper">
                    <input type="submit" value="Salvar" name="Salvar" />
                    <input type="button" value="Cancelar" onclick="submitForm('cancelar')" />
                </div>
            </form>

            <h2>Lista de Máquinas de Sorvete</h2>

            <table border="1">
                <c:if test="${not empty maquinas}">
                    <tr>
                        <th>Modelo</th>
                        <th>Capacidade</th>
                        <th>Data de Aquisição</th>
                        <th>Status</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="maquina" items="${maquinas}">
                    <tr>
                        <td>${maquina.modelo}</td>
                        <td>${maquina.capacidade}</td>
                        <td>${maquina.dataAquisicao}</td>
                        <td>${maquina.status}</td>
                        <td>    
                            <form action="${pageContext.request.contextPath}/controlador/MaquinaDeSorveteControlador" method="get">
                                <input type="hidden" name="idMaquina" value="${maquina.id}">
                                <input type="hidden" name="modeloMaquina" value="${maquina.modelo}">
                                <input type="hidden" name="capacidadeMaquina" value="${maquina.capacidade}">
                                <input type="hidden" name="dataAquisicaoMaquina" value="${maquina.dataAquisicao}">
                                <input type="hidden" name="statusMaquina" value="${maquina.status}">
                                <input type="hidden" name="opcao" value="editar">
                                <button type="submit" class="btn-edit">Editar</button>
                            </form>
                        </td>
                        <td>    
                            <form action="${pageContext.request.contextPath}/controlador/MaquinaDeSorveteControlador" method="get">
                                <input type="hidden" name="idMaquina" value="${maquina.id}">
                                <input type="hidden" name="modeloMaquina" value="${maquina.modelo}">
                                <input type="hidden" name="capacidadeMaquina" value="${maquina.capacidade}">
                                <input type="hidden" name="dataAquisicaoMaquina" value="${maquina.dataAquisicao}">
                                <input type="hidden" name="statusMaquina" value="${maquina.status}">
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
