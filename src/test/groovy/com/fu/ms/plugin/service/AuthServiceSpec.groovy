package com.fu.ms.plugin.service

import com.fu.ms.plugin.MDCInterceptor
import com.fu.ms.plugin.service.AuthService
import grails.test.mixin.TestFor
import org.apache.log4j.MDC
import org.springframework.mock.web.MockHttpServletRequest
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AuthService)
class AuthServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test auth failed as missing Authorization header"() {
        given:"http servlet request"
            MockHttpServletRequest request = new MockHttpServletRequest()
            request.method = 'GET'
            request.contextPath = '/some/path'
            //request.addHeader("Authorization", "fred")

            MDC.put(MDCInterceptor.REQUEST_ID, 'ABCD1234')

        when:"the request is parsed to json"
            def allowed = service.authenticateUser(request)

        then:"reject request"
            !allowed
    }

    void "test auth failed as invalid user in Authorization header"() {
        given:"http servlet request"
        MockHttpServletRequest request = new MockHttpServletRequest()
            request.method = 'GET'
            request.contextPath = '/some/path'
            request.addHeader("Authorization", "fred")

            MDC.put(MDCInterceptor.REQUEST_ID, 'ABCD1234')

        when:"the request is parsed to json"
            def allowed = service.authenticateUser(request)

        then:"reject request"
            !allowed
    }

    void "test auth ok"() {
        given:"http servlet request"
            MockHttpServletRequest request = new MockHttpServletRequest()
            request.method = 'GET'
            request.contextPath = '/some/path'
            request.addHeader("Authorization", "bob")

            MDC.put(MDCInterceptor.REQUEST_ID, 'ABCD1234')

        when:"the request is parsed to json"
            def allowed = service.authenticateUser(request)

        then:"allow request"
            allowed
    }
}
