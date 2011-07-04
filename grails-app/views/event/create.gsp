<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create New Event</title>
    <meta name="layout" content="main">
    <g:javascript library="eventInteractions"/>
</head>

<body>
<h1>Create Event</h1>

<form action="save" name="createEventForm">
    <label>Title</label>
    <g:textField id="event-title" name="event-title"/><br><br>
    <label>Date</label>
    <g:textField name="dateField" id="event-date"/><br><br>
    <script type="text/javascript">
        $(function() {
            frontlinesms.activateDatePicker();
        });
    </script>
    <input type="submit" id="event-save" value="Save"/>
    <button id="event-cancel" onclick="frontlinesms.eventCancelConfirmAction();
    return false;">Cancel</button>

</form>
<div id="event-cancel-dialog" title="Cancel event creation?" style="display: none;">
    <p>Are you sure you want to cancel this event? Your event will not be saved?</p>
</div>
</body>
</html>