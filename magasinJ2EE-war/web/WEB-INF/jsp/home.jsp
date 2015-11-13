<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/marges/banniere.jsp" />
        <div>
            <c:url value="ControleurMV2?section=catalogue&action=voir" var="url01" />
            <c:import url="${url01}" />
        </div>
        <div>
            <c:url value="ControleurMV2?section=afficherPanier" var="url02" />
            <c:import url="${url02}" />
        </div>

        <c:import url="/WEB-INF/jsp/marges/footer.jsp" />
    </body>
</html>
