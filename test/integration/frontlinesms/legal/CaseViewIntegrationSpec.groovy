package frontlinesms.legal

import grails.plugin.spock.IntegrationSpec

/**
 * Created by IntelliJ IDEA.
 * User: devam
 * Date: 6/30/11
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
class CaseViewIntegrationSpec extends IntegrationSpec{

    def "save should change the database state"(){
        given:
        def newCase = new Case(caseId: "1234", description: "Whatever")
        when:
        newCase.save(flush:true)
        then:
        Case.count() == 1
    }

    def "retrieve case details from database"(){
        given:
        def newCase = new Case(caseId: "1234", description: "Whatever")
         def caseController=new CaseViewController()
        when:
        newCase.save(flush: true)
        caseController.params.id="1234"
          then:
      def caseDetails= caseController.caseview().theCase
         caseDetails.caseId=="1234"
        caseDetails.description=="Whatever"
    }
}
