<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Simple GSP page</title>
    <meta name="layout" content="main">
    <g:javascript library="linkContacts"/>
    <script type="text/javascript">
        $(function() {
            frontlinesms.linkContacts();
        })
    </script>
</head>

<body>
<h1>Case Details</h1>
<g:form action="save" id="save-case-form">
    <label>Case Number</label>
    <g:hiddenField name="currentId" id="current-id" value="${caseToDisplay.id}"/>
    <g:textField name="caseId" id="case-id" value="${caseToDisplay.caseId}"/><br>
    <label>Description</label>
    <g:textArea name="description" id="case-description" cols="100" rows="10" value="${caseToDisplay.description}"/>

    <g:if test="${contacts?.size > 0}">
        <table name="contacts" id="contacts">
            <tr>
                <th>Contact Name</th>
                <th>Phone</th>
                <th>Contact Type</th>
            </tr>
            <g:each in="${contacts}">
                <tr>
                    <td><g:textField value="${it.contact.name}" name="contactName"/></td>
                    <td><g:textField value="${it.phoneNumber}" name="contactPhone"/></td>
                    <td><g:textField value="${it.type}" name="contactType"/></td>
                </tr>
            </g:each>
        </table>
    </g:if>

    <g:actionSubmit id="case-save" value="Save"/>

</g:form>

<div id="link-contacts" title="Link Contacts">
    <g:form action="">
        <g:textField name="contactNameSearch" id="contact-name-search"/>
        <g:actionSubmit id="search-button" value="Search"/>
    </g:form>
</div>
<button id="link-contact-button">Link contacts</button>

</body>

</html>