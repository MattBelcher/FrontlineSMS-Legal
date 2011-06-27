<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Calendar" /></title>

        <script src="../js/jquery-1.5.2.min.js" type="text/javascript" />
        <script src="../js/jquery-ui-1.8.11.custom.min.js" type="text/javascript" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <link href="${resource(dir:'css',file:'fullcalendar.css')}" rel="stylesheet" />
        <link href="${resource(dir:'css',file:'fullcalendar.print.css')}" rel="stylesheet" />
        <g:layoutHead />
        <g:javascript library="application" />
        <g:javascript library="fullcalendar" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>