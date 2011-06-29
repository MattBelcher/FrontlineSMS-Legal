<%--
  Created by IntelliJ IDEA.
  User: Thoughtworks
  Date: 6/29/11
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Case View</title></head>

<body>
<g:form action="save">
    <g:textField name="id" id="case-id" value="1234" />
    <g:textField name="description" id="case-description"/>
    <g:actionSubmit id="case-save" value="save"/>
</g:form>
</body>
</html>