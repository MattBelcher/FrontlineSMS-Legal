
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Contact Search Page</title>
   <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
    <g:javascript library="picnet.table.filter.min"/>
    <g:javascript library="contactSearchForSearchPage"/>
     <script type="text/javascript">
      $(function(){
          frontlinesms.contactSearchOnLoad();
      });
     </script>
  </head>
  <body>

  <h1 class="form-header">Search for Contact by name</h1>

<g:form action="search" method="POST" >
    <label>Enter the contact Name to search for a contact</label>
    <g:textField class="wide-text-box" name="contact-search-bar" id="contact-search-bar" value=""/>
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
                        <g:link controller="legalContact" action="show" id="${legalContact.id}"><%=legalContact.name%></g:link>
                    </td>
                    <td>
                        <g:link controller="legalContact" action="show" id="${legalContact.id}"><%=legalContact.primaryMobile%></g:link>

                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>

    </g:form>
  </body>

</html>