package com.zhuochen.spring.session.config.security.session;

import com.zhuochen.spring.session.config.constants.CommonSymbol;
import com.zhuochen.spring.session.service.CommonStaticService;
import com.zhuochen.spring.session.web.common.WrappedSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
public class AuthHttpSessionIdResolver extends HeaderHttpSessionIdResolver {

    public static AuthHttpSessionIdResolver xAuthToken() {
        return new AuthHttpSessionIdResolver(CommonSymbol.HEADER_X_AUTH_TOKEN);
    }

    public AuthHttpSessionIdResolver(String headerName) {
        super(headerName);
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        log.info("setSessionId: " + sessionId);
        Optional.ofNullable(sessionId)
                .ifPresent(s -> {
                    String deviceId = request.getHeader(CommonSymbol.USER_AGENT);
                    String ipAddress = request.getRemoteHost();
                    WrappedSession session = new WrappedSession(deviceId, ipAddress, sessionId);
                    super.setSessionId(request, response, CommonStaticService.encrypt(CommonStaticService.convertObjectToJson(session)));
                });
    }

    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        List<String> list =  Optional.ofNullable(request.getHeader(CommonSymbol.HEADER_X_AUTH_TOKEN))
                .map(headerValue -> CommonStaticService.decrypt(headerValue)
                        .map(value -> {
                            WrappedSession session = CommonStaticService.getObjectFromJson(value, WrappedSession.class);
                            return Optional.ofNullable(session)
                                    .map(s -> {
                                        log.info("resolveSessionIds: " + s.getSessionId());
                                        return Collections.singletonList(s.getSessionId());
                                    })
                                    .orElse(Collections.emptyList());
                        }).orElse(Collections.emptyList())).orElse(Collections.emptyList());
        log.info(list.size());
        return list;
    }
}
