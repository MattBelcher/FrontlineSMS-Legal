<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create New Event</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
    <g:javascript library="picnet.table.filter.min"/>
    <g:javascript library="jquery.timeentry.min"/>
    <g:javascript library="eventInteractions"/>
    <g:javascript library="linkContactToEvent"/>
    <g:javascript library="contactSearch"/>

    <script type="text/javascript">
        $(function() {
            frontlinesms.linkContactToEvent();
            frontlinesms.contactSearchOnLoad();

        })
    </script>


</head>

<body>
<h1>Create Event</h1>

<form action="save" name="createEventForm" method="post">
    <label>Title</label>
    <g:textField id="event-title" name="eventTitle" value="${params.eventTitle}"/><br><br>
    <label>Date</label>
    <g:textField name="dateFieldSelected" id="event-date" value="${params.dateFieldSelected}"/><br><br>
    <label>Start time</label>
    <g:textField name="startTimeField" id="event-start-time" value="${params.startTimeField}"/><br><br>
    <label>End time</label>
    <g:textField name="endTimeField" id="event-end-time" value="${params.endTimeField}"/><br><br>
    <input type="submit" id="event-save" value="Save"/>
    <button id="event-cancel" onclick="frontlinesms.eventCancelConfirmAction();
    return false;">Cancel</button>


    <div class="form-submit-area">
        <button id="link-contact-button">Link contacts</button>
    </div>

    <g:hiddenField name="linkedContacts" id="event-linked-contacts" value="${params.linkedContacts}" />
    <table name="contacts" id="contacts">
        <tr>
            <th>Contact Name</th>
            <th>Phone</th>
        </tr>

    </table>


</form>

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


<div id="event-cancel-dialog" title="Cancel event creation?" style="display: none;">
    <p>Are you sure you want to cancel this event? Your event will not be saved?</p>
</div>
<script type="text/javascript">
    $(function() {
        frontlinesms.activateDatePicker();
        frontlinesms.activateTimePicker();
    });
</script>
</body>
</html>