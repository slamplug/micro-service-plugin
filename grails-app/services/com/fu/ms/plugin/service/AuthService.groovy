package com.fu.ms.plugin.service

import grails.transaction.Transactional
import groovy.util.logging.Log4j
import org.apache.commons.lang.StringUtils

import javax.servlet.http.HttpServletRequest

@Transactional
@Log4j
class AuthService {

    private def allowedUsers = [ 'bob', 'jim']

    def authenticateUser(HttpServletRequest request) {
        def user = request.getHeader("Authorization")
        if (StringUtils.isEmpty(user)) {
            log.warn("no Authorization header")
            return false
        }
        if (!allowedUsers.contains(user)) {
            log.warn("user not in authorized list")
            return false
        }
        log.info "user authenticated"
        return true
    }
}
