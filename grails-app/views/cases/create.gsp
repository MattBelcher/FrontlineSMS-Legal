<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Simple GSP page</title></head>

<body>
<g:form action="save">
    <g:textField name="id" id="case-id"/>
    <g:textField name="description" id="case-description"/>
    <g:actionSubmit id="case-save" value="save"/>
</g:form>
</body>
</html>