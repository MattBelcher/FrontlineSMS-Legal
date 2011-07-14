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
        <label>Contact Name</label>
        <g:textField name="name" id="contact-name" value="${params.contactName}"/>
        <label>Primary Number</label>
        <g:textField name="primaryMobile" id="contact-primary-mobile" value="${params.primaryMobile}"/>
        <label>Notes</label>
        <g:textArea name="notes" id="contact-notes" value="${params.notes}" cols="100" rows="10"/>

        <div class="form-submit-area">
            <button id="contact-save">Save</button>
            <button id="contact-create-cancel">Cancel</button>
        </div>

    </form>

    <div id="contact-save-no-name-dialog" title="Save Contact without Contact Name?" style="display: none;">
        <p>Are you sure you would like to save contact without a name?</p>
    </div>

  <div id="contact-create-cancel-dialog" title="Cancel Contact Creation?" style="display: none;">
    <p>Do you want to cancel creation of this contact? Your changes will not be saved.</p>
</div>

  </body>
</html>
