<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>ShowCasePage</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
    <g:javascript library="linkContactToCase"/>
    <g:javascript library="updateCase"/>
    <g:javascript library="picnet.table.filter.min"/>
    <g:javascript library="contactSearch"/>
    <script type="text/javascript">
        $(function() {
            frontlinesms.linkContactToCase();
            frontlinesms.contactSearchOnLoad();
        })
    </script>
</head>

<body>
<h1 class="form-header">Case Details</h1>
<g:form action="update" id="save-case-form">
    <label>Case Number</label>
    <g:hiddenField name="currentId" id="current-id" value="${caseToDisplay.id}"/>
    <g:textField name="caseId" id="case-id" value="${caseToDisplay.caseId}"/>
    <label>Description</label>
    <g:textArea name="description" id="case-description" cols="100" rows="10" value="${caseToDisplay.description}"/>
    <p><g:checkBox name="case-status" id="case-status" value="${caseToDisplay.active}"/>Case active</p>

    <div class="form-submit-area">
        <button id="link-contact-button">Link contacts</button>
    </div>
    <table name="contacts" id="contacts">
        <tr>
            <th>Contact Name</th>
            <th>Phone</th>
            <th>Involvement</th>
        </tr>
        <g:if test="${contacts?.size > 0}">
            <g:each in="${contacts}">
                <tr>
                    <td><g:textField value="${it.name}" name="contactName"/></td>
                </tr>
            </g:each>
        </g:if>
    </table>

    <div class="form-submit-area">
        <g:actionSubmit id="case-update" value="Update"/>
        <button id="case-update-cancel" onclick="frontlinesms.caseUpdateCancelConfirmAction();
        return false;">Cancel</button>
    </div>
</g:form>

<div id="link-contacts" title="Link Contacts">
    <g:form action="">
        <g:textField name="contactNameSearch" id="contact-name-search"/>
        <table id="link-contacts-outer-table">
            <thead>
            <tr><td>Name</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div id="link-contacts-inner-table-div" style="height:200px;overflow: scroll; width:200px">
                        <table id="contactsTable">
                            <thead>
                            <tr><td>Name</td></tr>
                            </thead>
                            <tbody>
                            <g:each in="${contactList}" var="contact">
                                <tr class="contactLink" id="${contact.name}">

                                    <td class="contact-name">
                                        <a href="#"><%=contact.name%></a>
                                    </td>

                                    <td class="contact-number">
                                        <a href="#"><%=contact.primaryMobile%></a>
                                    </td>

                                </tr>
                            </g:each>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

    </g:form>
</div>

<div id="case-cancel-dialog" style="display: none;">
    <p>Do you wish to discard these changes?</p>
</div>

</body>

</html>