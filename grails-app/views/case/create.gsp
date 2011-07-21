<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>

    <g:javascript library="caseCreate"/>
    <g:javascript library="linkContactToCase"/>
    <g:javascript library="picnet.table.filter.min"/>
    <g:javascript library="contactSearch"/>

    <title>Create New Case</title>
    <script type="text/javascript">
        $(function() {
            frontlinesms.createNewCaseOnLoad();
            frontlinesms.linkContactToCase();
        })
    </script>
</head>

<body>

<h1 class="form-header">Case Details</h1>
<g:form action="save" method="post">

    <label>Case Number</label>
    <g:textField name="caseId" id="case-id"/>
    <label>Description</label>

    <g:if test="${!params.description}">
        <g:textArea name="description" id="case-description" cols="100" rows="10"/>
    </g:if>

    <g:if test="${params.description}">
        <g:textArea name="description" id="case-description" cols="100" rows="10" value="${params.description}"/>
    </g:if>

    <div class="form-submit-area">
        <button id="link-contact-button">Link contacts</button>
    </div>


    <table name="contacts" id="contacts">
        <tr>
            <th>Contact Name</th>
            <th>Phone</th>
            <th>Relationship</th>
        </tr>
        <g:if test="${contacts?.size > 0}">
        <g:each in="${contacts}">
        <tr name="contactRow" id="contact-row">
        <td><g:textField value="${it.name}" name="contactName"/></td>
        </tr>
        </g:each>
        </g:if>
    </table>


    <div class="form-submit-area">
        <input type="submit" id="case-save" value="Save"/>
        <button id="case-cancel">Cancel</button>
    </div>

    <g:hiddenField name="linkedContactIds" id="linked-contact-ids" value=""/>
    <g:hiddenField name="involvementList" id="involvement-list" value=""/>

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
                            </thead>
                            <tbody>
                            <g:each in="${contactList}" var="contact">

                                <tr class="contactLink" id="${contact.id}">

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

<div id="case-cancel-dialog" title="Cancel case creation?" style="display: none;">
    <p>Are you sure you do not want to save this case?</p>
</div>

</body>
</html>