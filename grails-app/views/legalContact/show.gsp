<%--
  Created by IntelliJ IDEA.
  User: Thoughtworks
  Date: 7/13/11
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <meta name="layout" content="main">
      <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>

    <title>Show Contact Page</title>
  </head>
  <body>
  <h1 class="form-header">Case Details</h1>
  <g:form action="save" id="contact-save-form">
      <label>Contact Name</label>
          <g:textField name="name" id="contact-name" value="${contactToDisplay.name}"/>
      <label>Primary Mobile</label>
                <g:textField name="primaryMobile" id="contact-primary-mobile" value="${contactToDisplay.primaryMobile}"/>
      <label>Notes</label>
                      <g:textField name="notes" id="contact-notes" value="${contactToDisplay.notes}"/>


  </g:form>
 <div class="form-submit-area" id="link-cases" title="Link Case to Contact">
    <button id="link-case-button">Link Cases</button>
</div>
  </body>
</html>