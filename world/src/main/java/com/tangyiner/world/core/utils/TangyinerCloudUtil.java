/*
 * Copyright (c) 2014-2019 2019 杭州派迩瑞筹信息技术有限公司 All Rights Reserved.
 * ProjectName: pi-cloud
 * FileName: PiCloudUtil.java
 * Author: YuZheng
 * Date: 19-5-21 下午4:43
 * Version: 1.0
 * LastModified: 19-5-20 下午1:26
 */
package com.tangyiner.world.core.utils;


import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuzheng
 * @create 2018-12-13 15:36
 */

public class TangyinerCloudUtil {

    private static final Logger LOGGER = LoggerManager.getRunLog();

    private static final int MAX_BODY_LENGTH = 1024 * 1024; //body最大记录1M

    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            headersMap.put(key, value);
        }
        return headersMap;
    }

    /**
     * 获取http请求内容
     *
     * @param request
     * @return
     */
    public static String getHttpRequestString(HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        //http请求行: method + url + protocol
        StringBuilder sb = new StringBuilder();
        sb.append(request.getMethod());
        sb.append(" ");
        sb.append(request.getRequestURL());
        if (!StringUtils.isEmpty(request.getQueryString())) {
            sb.append("?");
            sb.append(request.getQueryString());
        }
        sb.append(" ");
        sb.append(request.getProtocol());
        sb.append(System.lineSeparator());

        //http header
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            sb.append(name);
            sb.append(": ");
            sb.append(request.getHeader(name));
            sb.append(System.lineSeparator());
        }

        //http body
        //文件上传不记录body, post并且ContentType中包含multipart
        boolean isMultipart = request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString()) &&
                !StringUtils.isEmpty(request.getContentType()) && request.getContentType().toLowerCase().contains("multipart");
        if (!isMultipart) {
            ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    String body = null;
                    try {
                        int length = Math.min(buf.length, MAX_BODY_LENGTH);
                        body = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.debug("读取HttpServletRequest body出错", e);
                    }
                    if (!StringUtils.isEmpty(body)) {
                        sb.append(System.lineSeparator());
                        sb.append(body);
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * 获取http返回内容
     *
     * @param protocol
     * @param response
     * @return
     */
    public static String getHttpResponseString(String protocol, HttpServletResponse response) {
        if (response == null) {
            return "";
        }

        //http状态行: protocol + status
        StringBuilder sb = new StringBuilder();
        sb.append(protocol);
        sb.append(" ");
        sb.append(Integer.toString(response.getStatus()));
        sb.append(System.lineSeparator());

        //http header
        for (String name : response.getHeaderNames()) {
            sb.append(name);
            sb.append(": ");
            sb.append(response.getHeader(name));
            sb.append(System.lineSeparator());
        }

        //http body
        //只记录返回类型是json的body, ContentType中包含json
        boolean isJson = !StringUtils.isEmpty(response.getContentType()) && response.getContentType().toLowerCase().contains("json");
        if (isJson) {
            ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    String body = null;
                    try {
                        int length = Math.min(buf.length, MAX_BODY_LENGTH);
                        body = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.debug("读取HttpServletResponse body出错", e);
                    }
                    if (!StringUtils.isEmpty(body)) {
                        sb.append(System.lineSeparator());
                        sb.append(body);
                    }
                }
            }
        }

        return sb.toString();
    }
}
