package com.fu.ms.plugin.service

import com.fu.ms.plugin.MDCInterceptor
import grails.test.mixin.TestFor
import org.apache.log4j.MDC
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AuditService)
class AuditServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test parse request object to JSON ready for auditing"() {
        given:"http servlet request"
            MockHttpServletRequest request = new MockHttpServletRequest()
            request.method = 'GET'
            request.requestURI = '/some/path'

            MDC.put(MDCInterceptor.REQUEST_ID, 'ABCD1234')

        when:"the request is parsed to json"
            def json = service.requestToJson(request)

        then:"the json should contain"
            json.requestId == 'ABCD1234'
            json.appName == 'micro-service-plugin'
            json.method == 'GET'
            json.requestURI == '/some/path'
    }

    void "test parse response object to JSON ready for auditing"() {
        given:"http servlet response"
        MockHttpServletResponse response = new MockHttpServletResponse()
        response.status = 200

        MDC.put(MDCInterceptor.REQUEST_ID, 'ABCD1234')

        when:"the request is parsed to json"
        def json = service.responseToJson(response)

        then:"the json should contain"
        json.requestId == 'ABCD1234'
        json.appName == 'micro-service-plugin'
        json.status == 200
    }
}
