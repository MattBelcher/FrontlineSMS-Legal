<%--
  Created by IntelliJ IDEA.
  User: Thoughtworks
  Date: 6/27/11
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>Simple GSP page</title></head>
  <body>
  <jqueryCalendar:monthResources />
  <jqueryCalendar:month
        year="${year}"
        month="${month}"
        weekStart="${1}"
        draggable="${true}"
        readonly="${readOnly}"
        fixedWeeks="${true}"
        abbrevDayHeadings="${false}"
        title="${true}"
        showTime="guess" />
  <script type="text/javascript" src='../js/calendar-override.js'></script>
  </body>
</html>