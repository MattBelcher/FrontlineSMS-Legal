<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="jquery-1.6.1" />
        <g:layoutHead />
    </head>
    <body>
        <div id="header"></div>
        <div id="content-wrapper">
            <div id="sidebar"></div>
            <div id="main-content"><g:layoutBody /></div>
        </div>
        <div id="footer"></div>
    </body>
</html>
