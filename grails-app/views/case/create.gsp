<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:javascript library="caseCreate" />
    <title>Create New Case</title>
</head>

<body>
<h1>Case Details</h1>  <br><br><br>
<g:if test="${flash.error}">
    <div id="errorMessage">${flash.error}</div>
</g:if>

<form action="save" method="post">
    <label>Case Number </label>
    <g:textField name="caseId" id="case-id"/>  <br>
    <label>Description </label>
    <g:textArea name="description" id="case-description" cols="100" rows="10"/><br>
    <input type="submit" id="case-save" value="Save"/>
    <button id="case-cancel" onclick="frontlinesms.cancelConfirmAction(); return false;">Cancel</button>
</form>

<div id="case-cancel-dialog" title="Cancel case creation?" style="display: none;">
    <p>Are you sure you do not want to save this case?</p>
</div>

</body>
</html>