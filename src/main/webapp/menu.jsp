<!DOCTYPE html>
<html lang="pt">
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <script src="https://unpkg.com/@phosphor-icons/web"></script>
        <meta charset="UTF-8">
        <title>Software Gestão de Sorveterias</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.module.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Index.module.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merienda:wght@300..900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Merienda:wght@300..900&family=Raleway:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar">
            <ul class="menu-items">
                <li>
                    <i class="ph ph-house"></i>
                    <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li>
                    <i class="ph ph-cash-register"></i>
                    <a href="${pageContext.request.contextPath}/controlador/VendaControlador">Inserir venda</a>
                </li>
                <li>
                    <i class="ph ph-star"></i>
                    <a href="${pageContext.request.contextPath}/controlador/AvaliacaoControlador">Inserir avaliação</a>
                </li>
                <li>
                    <i class="ph ph-database"></i>
                    <a href="${pageContext.request.contextPath}/Database.jsp">Banco de Dados</a>
                </li>
            </ul>
            <form class="logout-form" name="logout" method="post" action="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">  
                <i class="ph ph-sign-out"></i>
                <input type="submit" value="Logout">
                <input type="hidden" name="opcao" value="logout" />
            </form>
        </nav>
    </body>
</html>
