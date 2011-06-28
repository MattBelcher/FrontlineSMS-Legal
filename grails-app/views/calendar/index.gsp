<%--
  Created by IntelliJ IDEA.
  User: Thoughtworks
  Date: 6/27/11
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Simple GSP page</title>
  </head>
  <body>
    <div id="left"></div>
    <div id="right"><div id="calendar"></div></div>

    <script type="text/javascript">
        $('#calendar').fullCalendar({
            weekMode: 'liquid'
        });
    </script>
  </body>
</html>