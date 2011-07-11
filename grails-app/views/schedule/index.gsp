<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'schedule.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'theme.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.print.css')}" media="print"/>
    <g:javascript library="fullcalendar"/>
    <g:javascript library="calendarInteractions"/>
    <g:javascript library="eventDetails"/>

    <script type="text/javascript">
        $(function() {
            frontlinesms.calendarInteractions();
            frontlinesms.eventDetails();
            $('#event-id').hide();
        });
    </script>
    <title>Schedule</title>
</head>

<body class="schedule">
    <div id="schedule"></div>

<div id="view-event" title="Event Details">
    <label>Title :</label>
    <label name="eventTitle" id="event-title"></label><br/>
    <label>Date :</label>
    <label name="eventDate" id="event-date"></label><br/>
    <input type="button" id="delete-event" value="Delete"/>
    <label name="eventId" id="event-id"></label>

</div>

<div id="event-cancel-dialog" title="Delete Event?" style="display: none;">
                <p>Are you sure you want to delete this appointment? Yes or No.</p>
            </div>
</body>
</html>