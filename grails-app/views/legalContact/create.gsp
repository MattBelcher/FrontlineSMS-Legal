<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <meta name="layout" content="main">
      <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
      <g:javascript library="contactCreate"/>

      <title>Create New Contact</title></head>
  <body>
    <h1 class="form-header">Contact Details</h1>

    <form action="save" method="post">
        <g:textField name="contactName" id="contact-name" value="${params.contactName}"/>
        <g:textField name="primaryMobile" id="contact-primary-mobile" value="${params.primaryMobile}"/>
        <g:textField name="notes" id="contact-notes" value="${params.notes}"/>
        <button id="save" onclick="frontlinesms.contactCreateWithoutNameConfirmAction();">Save</button>
    </form>

    <div id="case-save-without-name-dialog" title="Save Contact without Contact Name?" style="display: none;">
        <p>Are you sure you would like to save contact without a name?</p>
    </div>

  </body>
</html>
