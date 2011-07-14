<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <meta name="layout" content="main">
      <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
      <g:javascript library="contactCreate"/>

      <title>Create New Contact</title>
      <script type="text/javascript">
          $(function() {
              frontlinesms.createNewContactOnLoad();
          })
      </script>

  </head>
  <body>
    <h1 class="form-header">Contact Details</h1>

    <form action="save" method="POST" id="contact-save-form">
        <g:textField name="name" id="contact-name" value="${params.contactName}"/>
        <g:textField name="primaryMobile" id="contact-primary-mobile" value="${params.primaryMobile}"/>
        <g:textField name="notes" id="contact-notes" value="${params.notes}"/>

        <div class="form-submit-area">
            <button id="contact-save">Save</button>
            <button id="contact-create-cancel">Cancel</button>
        </div>

    </form>

    <div id="contact-save-no-name-dialog" title="Save Contact without Contact Name?" style="display: none;">
        <p>Are you sure you would like to save contact without a name?</p>
    </div>

  </body>
</html>
