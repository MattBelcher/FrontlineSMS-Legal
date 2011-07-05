<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Simple GSP page</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
</head>

<body>
<h1 class="form-header">Case Details</h1>
<g:form action="save" id="save-case-form">
    <label>Case Number</label>
    <g:hiddenField name="currentId" id="current-id" value="${caseToDisplay.id}"/>
    <g:textField name="caseId" id="case-id" value="${caseToDisplay.caseId}"/>
    <label>Description</label>
    <g:textArea name="description" id="case-description" cols="100" rows="10" value="${caseToDisplay.description}"/>
    <div class="form-submit-area">
        <g:actionSubmit id="link-contact" value="Link contacts" action="linkContact"/>
    </div>
    <table name="contacts" id="contacts">
    <tr>
        <th>Contact Name</th>
        <th>Phone</th>
        <th>Contact Type</th>
    </tr>
    <g:if test="${contacts?.size > 0}">
            <g:each in="${contacts}">
                <tr>
                    <td><g:textField value="${it.contact.name}" name="contactName"/></td>
                    <td><g:textField value="${it.phoneNumber}" name="contactPhone"/></td>
                    <td><g:textField value="${it.type}" name="contactType"/></td>
                </tr>
            </g:each>
    </g:if>
    </table>

    <div class="form-submit-area">
        <g:actionSubmit id="case-save" value="Save"/>
    </div>
</g:form>
</body>

</html>