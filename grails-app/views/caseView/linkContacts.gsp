<%--
  Created by IntelliJ IDEA.
  User: devam
  Date: 6/30/11
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Link Contacts</title></head>
  <body>
  <g:form action="search">
    <g:textField name="searchField" id="link-contacts" value="" />
    <g:actionSubmit id="submit-search" value="Search"/>
</g:form>
  <table name="contactsList" id="filteredContacts">
      <tr>
          <th ><span id="name-header" name="nameCellofContactsTable" value="Contacts">Contacts</span></th>
          <th id="phone-header">Phone Number</th>
      </tr>
  </table>
      </div>
  </body>
</html>