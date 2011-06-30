package frontlinesms.legal

class CaseViewController {

    def caseview = {
          [ theCase : Case.findByCaseId(params.id)]
    }

    def linkContacts={

    }

    def link={
          redirect {action:linkContacts}
    }
}