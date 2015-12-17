package com.fu.ms.plugin.service

import com.fu.ms.plugin.MDCInterceptor
import grails.converters.JSON
import grails.core.GrailsApplication
import grails.transaction.Transactional
import groovy.json.JsonOutput
import groovy.util.logging.Log4j
import org.slf4j.MDC

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Transactional
@Log4j
class AuditService {

    GrailsApplication grailsApplication

    /**
     * Audit request
     * @param request
     * @return
     */
    def auditRequest(HttpServletRequest request) {
        log.info(requestToJson(request))
    }

    /**
     * request to Json
     * @param request
     * @return
     */
    def requestToJson(HttpServletRequest request) {
        def obj = [
                requestId: MDC.get(MDCInterceptor.REQUEST_ID),
                appName: grailsApplication.metadata.getApplicationName(),
                method: request.method,
                requestURI: request.requestURI
        ]
        return JSON.parse(JsonOutput.toJson(obj))
    }

    /**
     *
     */
    def auditResponse(HttpServletResponse response) {
        log.info(responseToJson(response))

    }

    /**
     * request to Json
     * @param request
     * @return
     */
    def responseToJson(HttpServletResponse response) {
        def obj = [
                requestId: MDC.get(MDCInterceptor.REQUEST_ID),
                appName: grailsApplication.metadata.getApplicationName(),
                status: response.status
        ]
        return JSON.parse(JsonOutput.toJson(obj))
    }
}
