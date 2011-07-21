
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Contact Search Page</title></head>
  <body>

    <g:if test="${foundContact}">
        <table class="search-results" id="SearchResults">
            <thead>
            <tr>
                <th>Name</th>
                <th>Mobile Number</th>
            </tr>
            </thead>
            <tbody>

            <g:each in="${foundContact}" var="legalContact">
                <tr>
                    <td>
                        <g:link controller="legalContact" action="show" id="${legalContact.name}"><%=legalContact.name%></g:link>
                    </td>
                    <td>
                        <g:link controller="legalContact" action="show" id="${legalContact.primaryMobile}"><%=legalContact.primaryMobile%></g:link>

                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>

  </body>

</html>