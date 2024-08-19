<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>

        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/Login.module.css">
    </head>
    <body>
        <header>
            <h1>Software para Sorveterias</h1>
        </header>
        <div class="formWrapper">

            <form id="cadastroForm" name="cadastro" method="post"  action="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">
                <p>
                    <label>Usuário:</label>
                    <input type="text" name="username" required>
                </p>
                <p>
                    <label>Senha:</label>
                    <input type="password" name="password" required>
                </p>
                <p>
                    <input type="submit" value="Login">
                </p>
                <input type="hidden" name="opcao" value="login" />
            </form>
            <a href="${pageContext.request.contextPath}/CadastroUsuario.jsp">Cadastrar Usuário</a>


            <c:if test="${not empty mensagem}">
                <p>${mensagem}</p>
            </c:if>
        </div>
    </body>
</html>