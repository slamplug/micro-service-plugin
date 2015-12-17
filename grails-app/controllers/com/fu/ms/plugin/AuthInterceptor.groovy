package com.fu.ms.plugin

import groovy.json.JsonOutput
import groovy.util.logging.Log4j
import org.springframework.http.HttpStatus

@Log4j
class AuthInterceptor {

    int order = 30

    def authService

    def forbidden = [
            status: HttpStatus.FORBIDDEN.value(),
            message: "Forbidden"
    ]

    public AuthInterceptor() {
        matchAll().excludes(uri: '/error')
    }

    boolean before() {
        log.info "authenticate user"
        if (!authService.authenticateUser(request)) {
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json"
            response.writer.write(JsonOutput.toJson(forbidden))
            response.writer.flush()
            false
        } else {
            true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
