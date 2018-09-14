package com.zhuochen.spring.session.config.interceptor;

import com.zhuochen.spring.session.config.constants.CommonSymbol;
import com.zhuochen.spring.session.service.CommonStaticService;
import com.zhuochen.spring.session.web.common.WrappedResponse;
import com.zhuochen.spring.session.web.common.WrappedSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String deviceId = request.getHeader(CommonSymbol.USER_AGENT);
        String sessionId = request.getHeader(CommonSymbol.HEADER_X_AUTH_TOKEN);
        String ipAddress = request.getRemoteHost();

        if (sessionId == null) {
            return true;
        } else {
            return CommonStaticService.decrypt(sessionId)
                    .map(s -> {
                        WrappedSession session = CommonStaticService.getObjectFromJson(s, WrappedSession.class);
                        if (session != null && deviceId.equals(session.getDeviceId()) && ipAddress.equals(session.getIpAddress())) {
                            return true;
                        } else {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            try {
                                response.getWriter().write(new WrappedResponse<>(null, HttpStatus.FORBIDDEN, "ip or device changed, please log in again").toString());
                            } catch (IOException e) {
                                log.error("response.getWriter() error: ", e);
                            }
                            return false;
                        }
                    }).orElse(false);
        }
    }
}
