package com.fu.ms.plugin

import groovy.util.logging.Log4j
import org.apache.log4j.MDC

@Log4j
class MDCInterceptor {

    public static final String REQUEST_ID = "REQUEST_ID"

    int order = 10

    public MDCInterceptor() {
        matchAll().excludes(uri: '/error')
    }


    boolean before() {
        log.info("setting request id in MDC context")
        MDC.clear()
        MDC.put(REQUEST_ID, UUID.randomUUID().toString());
        true
    }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
