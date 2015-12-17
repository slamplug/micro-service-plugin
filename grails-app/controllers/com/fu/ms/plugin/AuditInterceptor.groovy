package com.fu.ms.plugin

class AuditInterceptor {

    int order = 20

    def auditService

    public AuditInterceptor() {
        matchAll().excludes(uri: '/error')
    }

    boolean before() {
        auditService.auditRequest(request)
        true
    }

    boolean after() {
        auditService.auditResponse(response)
        true
    }

    void afterView() {
        // no-op
    }
}
