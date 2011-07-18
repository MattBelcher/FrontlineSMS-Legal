<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title>Show Contact Page</title>
      <meta name="layout" content="main">
      <link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}"/>
      <g:javascript library="linkCaseToContact"/>
      <g:javascript library="picnet.table.filter.min"/>
      <g:javascript library="caseSearch"/>
      <script type="text/javascript">
        $(function() {
            frontlinesms.linkCaseToContact();
            frontlinesms.caseSearchOnLoad();
        })
    </script>
  </head>
  <body>
  <h1 class="form-header">Case Details</h1>
  <g:form action="save" id="contact-save-form">
      <label>Contact Name</label>
          <g:textField name="name" id="contact-name" value="${contactToDisplay.name}"/>
      <label>Primary Mobile</label>
                <g:textField name="primaryMobile" id="contact-primary-mobile" value="${contactToDisplay.primaryMobile}"/>
      <label>Notes</label>
                      <g:textArea rows="10" cols="100" name="notes" id="contact-notes" value="${contactToDisplay.notes}"/>


  </g:form>

 <div class="form-submit-area">
    <button id="link-case-button">Link Cases</button>
</div>

<div id="link-case-dialog" title="Link Cases">
    <h3 class="form-header">Search for Case by Case ID</h3>

    <g:form action="search" method="POST">
        <label>Enter the case ID to search for cases</label>
        <g:textField class="wide-text-box" name="caseId" id="caseId"/>
        <div class="form-submit-area">
            <g:actionSubmit id="case-search" value="Search"/>
        </div>

        <g:if test="${foundCase}">
            <table class="search-results" id="SearchResults">
                <thead>
                <tr>
                    <th>Case ID</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>

                <g:each in="${foundCase}" var="legalCase">
                    <tr>
                        <td>
                            <g:link controller="contact" action="show" id="${legalCase.caseId}"><%=legalCase.caseId%></g:link>
                        </td>
                        <td>
                            <%= legalCase.active ? "active" : "inactive" %>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </g:if>
    </g:form>

</div>

  </body>
</html>