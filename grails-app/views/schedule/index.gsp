<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'fullcalendar.print.css')}" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'schedule.css')}" />
    <g:javascript library="fullcalendar"/>
    <g:javascript library="calendarInteractions"/>
    <script type="text/javascript">
        $(function() {
            frontlinesms.calendarInteractions();
        });
    </script>
    <title>Schedule</title>
</head>

<body>
<div id="schedule"></div>
</body>
</html>