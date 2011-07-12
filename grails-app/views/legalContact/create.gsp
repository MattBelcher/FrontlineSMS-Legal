
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>Create New Contact</title>
      <g:javascript library="mainLayout"/>
  </head>
  <body>
   <g:form action="show">
    <g:textField name="contactName" id="contact-name"/>
     <g:textField name="contactNumber" id="contact-number"/>
    <g:textField name="contactNotes" id="contact-notes"/>

  <input type="submit" id="contact-save" value="Save"/>

       </g:form>
  </body>

</html>