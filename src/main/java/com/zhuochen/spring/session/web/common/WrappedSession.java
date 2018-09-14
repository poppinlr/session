package com.zhuochen.spring.session.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrappedSession {

    private String deviceId;

    private String ipAddress;

    private String sessionId;
}
