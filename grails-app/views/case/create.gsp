<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Simple GSP page</title>
</head>

<body>
<h1>Case Details</h1>  <br><br><br>
<g:if test="${flash.error}">
    <div id="errorMessage">${flash.error}</div>
</g:if>

<g:form action="save">
    <label>Case Number </label>
    <g:textField name="caseId" id="case-id"/>  <br>
    <label>Description </label>
    <g:textArea name="description" id="case-description" cols="100" rows="10"/><br>
    <g:actionSubmit id="case-save" value="Save"/>
</g:form>
</body>
</html>