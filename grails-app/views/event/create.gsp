<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create New Event</title>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
    <g:javascript library="picnet.table.filter.min"/>
    <g:javascript library="jquery.timeentry.min"/>
    <g:javascript library="eventInteractions"/>


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

</form>
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