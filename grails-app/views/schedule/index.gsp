<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'schedule.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'theme.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}" media="print"/>
    <g:javascript library="fullcalendar"/>
    <g:javascript library="calendarInteractions"/>
    <g:javascript library="eventDetails"/>

    <script type="text/javascript">
        $(function() {
            $('#view-event').hide();
            frontlinesms.calendarInteractions();
            frontlinesms.eventDetails();
        });
    </script>
    <title>Schedule</title>
</head>

<body class="schedule">
<div id="schedule"></div>

<div id="view-event"  title="Event Details" style="display: none;">
    <label><b>Title</b></label><br/>
    <label name="eventTitle" id="event-title"></label><br/><br/>
    <label><b>Date</b></label><br/>
    <label name="eventDate" id="event-date"></label><br/><br/>
    <label><b>Start Time</b></label><br/>
    <label name="eventStartTime" id="event-start-time"></label><br/><br/>
    <label><b>End Time</b></label><br/>
    <label name="eventEndTime" id="event-end-time"></label><br/><br/>
    <div align="right"><input type="button" id="delete-event" value="Delete"/></div>
    <label style="display:none" name="eventId" id="event-id"></label>

</div>

<div id="event-cancel-dialog" title="Cancel case creation?" style="display: none;">
    <p>Are you sure you want to delete this event? Yes or No.</p>
</div>
</body>
</html>