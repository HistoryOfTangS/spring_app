package com.tangyiner.world.core;

/*
 * Copyright (c) 2014-2020 杭州派迩信息技术有限公司 All Rights Reserved.
 * ProjectName: pi-cloud
 * ModuleName: picloud-common-core
 * FileName: CoreConfiguration.java
 * Author: YuZheng
 * Date: 2020/04/27 11:26:27
 * Version: 1.0
 * LastModified: 2020/04/27 11:25:27
 */

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Core配置类
 */
@ConfigurationProperties("core-configuration")
public class CoreConfiguration {

    /**
     * 应用服务器编号, 多台应用服务器时不能重复, 允许值0..31
     */
    private static Integer serverId = 0;
    /**
     * 文件URL前缀
     */
    private static String fileUrlPrefix;

    /**
     * 根据客户端请求的主机, 获取文件URL前缀
     */
    private static Map<String, String> fileUrlPrefixMap = new HashMap<>();

    /**
     * 缓存过期时间(秒)
     */
    private static Integer cacheExpiration = 3600;

    /**
     * 本机ip地址
     */
    private static String localIp = "";

    /**
     * 是否启动服务心跳监控
     */
    private static Boolean enableServerMonitor = false;

    /**
     * 服务类型
     */
    private static String serverType = "PiCloudServer";

    /**
     * 是否启动druid的filter，包括美化sql，输出可执行的sql等，需要的logback-spring.xml中配置
     */
    private static Boolean enableSqlLogFilter = false;

    /**
     * 是否要启用数据修改日志记录
     */
    private static Boolean enableDataLogFilter = false;

    /**
     * 是否是生成环境，如果是就屏蔽对外抛出的异常堆栈，后面还有其他在增加
     */
    private static Boolean product = true;

    /**
     * redis的序列化方式，默认fastjson，
     * 注意： jacjson在反序列化Map对象时，key会变成string类型
     */
    private static String redisSerializeType = CoreConstants.REDIS_SERIALIZE_FASTJSON;

    public void setServerId(Integer serverId) {
        Assert.state(serverId != null && serverId >= 0 && serverId <= 31, "配置项CoreConfig.serverId表示应用服务器编号, 只能在0..31之间, 请检查!");
        CoreConfiguration.serverId = serverId;
    }

    public static Integer getServerId() {
        Assert.state(serverId != null && serverId >= 0 && serverId <= 31, "配置项CoreConfig.serverId表示应用服务器编号, 只能在0..31之间, 请检查!");
        return serverId;
    }

    public void setFileUrlPrefix(String fileUrlPrefix) {
        Assert.hasLength(fileUrlPrefix, "配置项CoreConfig.fileUrlPrefix表示文件URL前缀, 不可为空, 请检查!");
        CoreConfiguration.fileUrlPrefix = StringUtils.endsWith(fileUrlPrefix, "/") ? fileUrlPrefix : fileUrlPrefix + "/";
    }

    public static String getFileUrlPrefix() {
        Assert.hasLength(fileUrlPrefix, "配置项CoreConfig.fileUrlPrefix表示文件URL前缀, 不可为空, 请检查!");
        return fileUrlPrefix;
    }

    public void setFileUrlPrefixMap(Map<String, String> fileUrlPrefixMap) {
        CoreConfiguration.fileUrlPrefixMap = fileUrlPrefixMap;
    }

    public Map<String, String> getFileUrlPrefixMap() {
        return fileUrlPrefixMap;
    }

    public void setCacheExpiration(Integer cacheExpiration) {
        Assert.state(cacheExpiration != null && cacheExpiration >= 0, "配置项CoreConfig.cacheExpiration表示缓存过期时间, 不能小于0, 请检查!");
        CoreConfiguration.cacheExpiration = cacheExpiration;
    }

    public static Integer getCacheExpiration() {
        Assert.state(cacheExpiration != null && cacheExpiration >= 0, "配置项CoreConfig.cacheExpiration表示缓存过期时间, 不能小于0, 请检查!");
        return cacheExpiration;
    }

    /**
     * 根据客户端请求的服务器地址, 获取文件URL前缀
     *
     * @param hostName
     * @return
     */
    public static String getFileUrlPrefix(String hostName) {
        if (Strings.isNullOrEmpty(hostName) || fileUrlPrefixMap.isEmpty()) {
            return fileUrlPrefix;
        }

        String mapValue = fileUrlPrefixMap.get(hostName);
        if (!Strings.isNullOrEmpty(mapValue)) {
            return mapValue;
        }

        return fileUrlPrefix;
    }

    /**
     * 去除文件URL中的前缀
     *
     * @param fileUrl
     * @return
     */
    public static String cutFileUrlPrefix(String fileUrl) {
        if (fileUrl.contains(fileUrlPrefix)) {
            return fileUrl.replace(fileUrlPrefix, "");
        }
        for (String item : fileUrlPrefixMap.values()) {
            if (fileUrl.contains(item)) {
                return fileUrl.replace(item, "");
            }
        }
        return fileUrl;
    }

    public static String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        CoreConfiguration.localIp = localIp;
    }

    public static Boolean getEnableServerMonitor() {
        return enableServerMonitor;
    }

    public void setEnableServerMonitor(Boolean enableServerMonitor) {
        CoreConfiguration.enableServerMonitor = enableServerMonitor;
    }

    public static String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        CoreConfiguration.serverType = serverType;
    }

    public static Boolean getEnableSqlLogFilter() {
        return enableSqlLogFilter;
    }

    public void setEnableSqlLogFilter(Boolean enableSqlLogFilter) {
        CoreConfiguration.enableSqlLogFilter = enableSqlLogFilter;
    }

    public static Boolean getEnableDataLogFilter() {
        return enableDataLogFilter;
    }

    public void setEnableDataLogFilter(Boolean enableDataLogFilter) {
        CoreConfiguration.enableDataLogFilter = enableDataLogFilter;
    }

    public static String getRedisSerializeType() {
        return redisSerializeType;
    }

    public void setRedisSerializeType(String redisSerializeType) {
        CoreConfiguration.redisSerializeType = redisSerializeType;
    }

    public static Boolean getProduct() {
        return product;
    }

    public void setProduct(Boolean product) {
        CoreConfiguration.product = product;
    }
}
