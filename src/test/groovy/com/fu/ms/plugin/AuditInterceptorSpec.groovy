package com.fu.ms.plugin


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AuditInterceptor)
class AuditInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test audit interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"audit")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }

    void "Test audit interceptor does not match /error uri"() {
        when:"A request matches the interceptor"
            withRequest(uri:"/error")

        then:"The interceptor does match"
            !interceptor.doesMatch()
    }
}

