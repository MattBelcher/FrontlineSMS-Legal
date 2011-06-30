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

        <table width= 15% border=3>

            <tr>
                <th id="title">Case Id</th>

            </tr>

            <tr>
                <td align="center">

                        <g:link controller="caseView" action="caseview" id="${foundCase.caseId}"><div id="SearchResults"><%=foundCase.caseId%></g:link></div></td>
            </tr>

        </table>
    </div>
</g:if>
</body>
</html>