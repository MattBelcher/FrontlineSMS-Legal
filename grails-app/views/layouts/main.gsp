<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails"/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"/>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <g:javascript library="jquery-1.6.1"/>
    <g:javascript library="jquery-ui-1.8.14.custom.min"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.8.14.custom.css')}" />
    <g:layoutHead/>
</head>

<body>
<div id="header">
    <div id="logo"></div>
</div>

<div id="content-wrapper">
    <div id="sidebar">
        <ul>
            <li>Create New..
                <ul>
                     <g:link controller="case" action="create" name="createNewCase">Case</g:link>
                </ul>
            </li>
        </ul>
    </div>

    <div id="main-content"><g:layoutBody/></div>
</div>
</body>
</html>
