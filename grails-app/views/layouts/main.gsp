<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.8.14.custom.css')}" />
    <g:javascript library="jquery-1.6.1"/>
    <g:javascript library="jquery-ui-1.8.14.custom.min"/>
    <g:javascript library="mainLayout"/>
    <g:javascript library="tabs"/>
    <script type="text/javascript">
        $(function() {
            frontlinesms.tabs();
        });
    </script>
    <g:javascript library="jquery.timeentry.min"/>
    <g:layoutHead/>
</head>

<body>
<g:if test="${flash.error}">
    <div id="errorMessage"><span class="flash-message">${flash.error}</span></div>
</g:if>
<g:if test="${flash.message}">
    <div id="status"><span class="flash-message">${flash.message}</span></div>
</g:if>

<span id="active-tab" class="${pageProperty(name: "body.class")}"></span>
<div id="header">
    <div id="logo"></div>
    <div id="tabs">
        <div class="tab schedule">
            <g:link controller="schedule">Schedule</g:link>
        </div>
    </div>
</div>

<div id="content-wrapper">
    <div id="sidebar">
        <ul>
            <li>Create New..
                <ul>
                    <li><g:link controller="case" action="create" name="createNewCase">Case</g:link></li>
                    <li><g:link controller="event" action="create" name="createNewEvent">Event</g:link></li>
                </ul>
            </li>
        </ul>
    </div>

    <div id="main-content"><g:layoutBody/></div>
</div>
</body>
</html>
