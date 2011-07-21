package frontlinesms.legal.builders

import frontlinesms.legal.Case


class CaseBuilder {

    def  caseId = "1112"
    def description = "ertyui"

    def withCaseId(caseId){

        this.caseId = caseId
        this
    }

    def withDescription(description){

        this.description = description
        this
    }

    def build(){
        new Case(caseId:this.caseId, description: this.description)
    }

}
