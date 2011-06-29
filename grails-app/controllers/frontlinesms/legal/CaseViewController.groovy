package frontlinesms.legal

class CaseViewController {

    def caseview = {
          def theCase = Case.findByCaseId(params.id)

}
}