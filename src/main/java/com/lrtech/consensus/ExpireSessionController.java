package com.lrtech.consensus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExpireSessionController extends BasicController {

    private SessionRegistry sessionRegistry;

    @Autowired
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    protected void sessionExpireNow(HttpServletRequest request) {
        List<SessionInformation> sessionsToBeExpired = sessionRegistry.getAllSessions(authentication().getPrincipal(), false);
        for (SessionInformation session : sessionsToBeExpired) {
            if (!session.getSessionId().equals(request.getRequestedSessionId())) {
                session.expireNow();
            }
        }
    }

    protected void sessionExpireNow(String username) {
        Object principal = null;
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object o : principals) {
            String user = ((UserDetails) o).getUsername();
            if (user.equals(username)) {
                principal = o;
                break;
            }
        }

        sessionExpireNow(principal);
    }

    private void sessionExpireNow(Object principal) {
        if (principal != null) {
            List<SessionInformation> sessionsToBeExpired = sessionRegistry.getAllSessions(principal, false);
            for (SessionInformation session : sessionsToBeExpired) {
                session.expireNow();
            }
        }
    }
}