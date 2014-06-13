<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>        
    <body>
        <s:form action="Login">
            <table align="center">
                <tr><td>Login: <s:textfield name="login"/></tr></td>
                <tr><td>Senha: <s:password name="senha"/></tr></td>
                <tr><td><s:submit value="Log in!"/></tr></td>
                <tr><td><a href="cadastro.xhtml">cadastrar</a></tr></td>
            </table>
        </s:form>
</body>
</html>
