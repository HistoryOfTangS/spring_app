package com.tangyiner.world.core.inteceptor;

import com.tangyiner.world.core.CoreConstants;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截器, 处理Cross Origin Resource Sharing允许跨域访问
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {
    /**
     * cors配置
     */
    private final CorsConfiguration config = new CorsConfiguration();

    private CorsProcessor corsProcessor = new DefaultCorsProcessor();

    public CorsInterceptor() {
        config.addAllowedOrigin(CorsConfiguration.ALL);
        for (HttpMethod httpMethod : HttpMethod.class.getEnumConstants()) {
            config.addAllowedMethod(httpMethod);
        }
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedHeader(CoreConstants.X_TOKEN);
        config.addExposedHeader(CoreConstants.X_TOKEN);
        config.setAllowCredentials(true);
        config.setMaxAge(86400L);
    }

    /**
     * cors处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        corsProcessor.processRequest(config, request, response);
        return true;
    }
}