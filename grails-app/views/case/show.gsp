<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Simple GSP page</title>
    <meta name="layout" content="main">
</head>

<body>
<g:if test="${flash.error}">
    <div id="errorMessage">${flash.error}</div>
</g:if>
<g:if test="${flash.message}">
    <div id="status">${flash.message}</div>
</g:if>

<h1>Case Details</h1>

<g:form action="save">
    <label>Case Number</label>
    <g:textField name="caseId" id="case-id" value="${caseToDisplay.caseId}"/><br>
    <label>Description</label>
    <g:textArea name="description" id="case-description" cols="100" rows="10" value="${caseToDisplay.description}"/><br>
    <g:actionSubmit id="case-save" value="Save"/>
</g:form>
</body>
</html>