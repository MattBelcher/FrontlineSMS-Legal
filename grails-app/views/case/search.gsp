<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Search page</title>
    <meta name="layout" content="main">
</head>

<body>
<div align="center">
    <h1>Search for Case by Case Id</h1>

</div>

<br/><br/>

<div align="center">
    <p>Enter the case Id to search for cases</p>
    <br/>
    <g:form action="search" method="POST">
        <g:textField name="caseId" id="caseId"/>
        <g:actionSubmit id="case-search" value="Search"/>

    </g:form>
</div>
<g:if test="${foundCase}">
    <div align="center">
        <br/><br/>
        <br/><br/>

        <table width=15% border=3 id="SearchResults">
            <thead>
            <tr>
                <th id="title">Case Id</th>
            </tr>
            </thead>
            <tbody>

            <g:each in="${foundCase}" var="legalCase">
                <tr>

                <td align="center">

                <g:link controller="case" action="show"
                                id="${legalCase.caseId}"><%=legalCase.caseId%></g:link></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</g:if>
</body>
</html>