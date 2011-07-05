<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Search page</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
</head>

<body>
<h1 class="form-header">Search for Case by Case Id</h1>

<g:form action="search" method="POST">
    <label>Enter the case Id to search for cases</label>
    <g:textField class="wide-text-box" name="caseId" id="caseId"/>
    <div class="form-submit-area">
        <g:actionSubmit id="case-search" value="Search"/>
    </div>

    <g:if test="${foundCase}">
        <table class="search-results" id="SearchResults">
            <thead>
            <tr>
                <th id="title">Case Id</th>
            </tr>
            </thead>
            <tbody>

            <g:each in="${foundCase}" var="legalCase">
                <tr>
                    <td>
                        <g:link controller="case" action="show" id="${legalCase.caseId}"><%=legalCase.caseId%></g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>
</g:form>
</body>
</html>