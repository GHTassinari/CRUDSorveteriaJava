<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
        <meta charset="UTF-8">
        <title>Software Gestão de Sorveterias</title>
        <link rel="stylesheet" href="./css/Database.module.css">
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200..800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200..800&family=Noto+Sans:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1 class="tituloH1">Banco de Dados</h1>
        <div class="container mt-5">
            <div class="row">
                <div class="col-12">
                    <table class="table table-bordered">
                        <tbody>
                            <tr>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/ClienteControlador" class="btn btn-dark">Clientes</a>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/FornecedorControlador" class="btn btn-dark">Fornecedores</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/FuncionarioControlador" class="btn btn-dark">Funcionários</a>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/SorveteControlador" class="btn btn-dark">Tipos de sorvete</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/LojaControlador" class="btn btn-dark">Lojas disponíveis</a>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/MaquinaDeSorveteControlador" class="btn btn-dark">Máquinas de Sorvete</a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center">
                                    <a href="${pageContext.request.contextPath}/controlador/PagamentoControlador" class="btn btn-dark">Pagamentos recebidos</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
