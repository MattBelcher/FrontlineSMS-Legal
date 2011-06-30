<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Search page</title></head>

<body>

<g:form action="search" method="POST">
    <g:textField name="caseId" id="caseId"/>
    <g:actionSubmit id="case-search" value="Search"/>

</g:form>
<g:if test="${foundCase}">
    <div id="SearchResults"><%=foundCase.caseId%></div>
</g:if>
</body>
</html>