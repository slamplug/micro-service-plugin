package com.fu.ms.plugin


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MDCInterceptor)
class MDCInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test MDC interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"MDC")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
