package com.tangyiner.world.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.joda.time.DateTime;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公用常量定义
 *
 * @author yuzheng
 */
public final class CoreConstants {
    private CoreConstants() {
    }

    /**
     * 日期格式字符串
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 日期格式字符串-没有分隔符
     */
    public static final String DATE_FORMAT_NO_SEP = "yyyyMMdd";

    /**
     * 时间格式字符串
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 时间格式字符串-没有分隔符
     */
    public static final String TIME_FORMAT_NO_SEP = "HHmmss";

    /**
     * 日期时间格式字符串
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间格式字符串-没有分隔符
     */
    public static final String DATETIME_FORMAT_NO_SEP = "yyyyMMddHHmmss";

    /**
     * 日期时间格式字符串毫秒
     */
    public static final String DATETIME_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 日期时间格式字符串毫秒-没有分隔符
     */
    public static final String DATETIME_MS_FORMAT_NO_SEP = "yyyyMMddHHmmssSSS";

    /**
     * 最小日期
     */
    public static final Date MIN_DATE = new DateTime(1, 1, 1, 0, 0, 0).toDate();

    /**
     * 最大日期
     */
    public static final Date MAX_DATE = new DateTime(9999, 12, 31, 0, 0, 0).toDate();

    /**
     * 最小IP
     */
    public static final String MIN_IP = "0.0.0.0";

    /**
     * 最大IP
     */
    public static final String MAX_IP = "255.255.255.255";

    /**
     * 本机地址IPV4
     */
    public static final String LOCALHOST_V4 = "127.0.0.1";

    /**
     * 本机地址IPV6
     */
    public static final String LOCALHOST_V6 = "0:0:0:0:0:0:0:1";

    /**
     * IP地址正则表达式
     */
    public static final String IP_REGEXP = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    /**
     * true
     */
    public static final String TRUE = "true";

    /**
     * false
     */
    public static final String FALSE = "false";

    /**
     * true
     */
    public static final String YES = "1";

    /**
     * false
     */
    public static final String NO = "0";

    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * UTF-8 Charset
     */
    public static final Charset CHARSET_UTF_8 = Charset.forName(UTF_8);

    /**
     * 客户端有两种方式传递令牌: Header中X-Token, 或Cookie中X-Token
     */
    public static final String X_TOKEN = "authorization";

    public static final String X_REFRESH_TOKEN = "authorization-refresh";

    public static final String X_REMOTE_APP_VERSION = "map_remote_app_version";

    /**
     * 是由feign调用的标记
     */
    public static final String FEIGN_CLIENT = "feign-client";

    /**
     * 组织中文名称
     */
    public static final String GROUP_CHINESS_NAME = "杭州派迩信息技术有限公司";
    /**
     * 组织名称
     */
    public static final String GROUP_NAME = "www.pi-solution.com";

    /**
     * 组织email
     */
    public static final String GROUP_EMAIL_NAME = "zheng.yu@pi-solution.com";

    /**
     * AES加密密钥
     */
    public static final String AES_PASS = "com.pisolution";

    /**
     * jasypt加密密钥
     */
    public static final String JASYPT_PASS = "com.pisolution";

    public static final String JASYPT_ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";

    /**
     * 包前缀
     */
    public static final String PACKAGE_PREFIX = "com.pisolution";
    /**
     * 数据分页默认页码
     */
    public static final String PAGE_NUMBER = "1";
    /**
     * 数据分页默认笔数
     */
    public static final String PAGE_SIZE = "10";
    /**
     * 分隔符
     */
    public static final String SEPARATE_CHAR = "|";

    /**
     * redis的key的分隔符
     */
    public static final String REDIS_KEY_SEP_CHAR = ":";

    /**
     * i18n 在header中的key
     */
    public static final String HEADER_LANG_KEY = "lang";

    /**
     * i18n lang的语言与国家的分隔符
     */
    public static final String LANG_SPLIT = "_";

    /**
     * logback MDC中key
     */
    public static final String LOCAL_IP_MDC_KEY = "localIp";
    public static final String REQUEST_ID_MDC_KEY = "requestId";
    public static final String IN_TIMESTAMP_MDC_KEY = "in_timestamp";



    /**
     * 全局共用JSON转换工具
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 全局共用JSON转换工具, 动态类型
     */
    public static final ObjectMapper OBJECT_MAPPER_WITH_TYPE = new ObjectMapper();

    public static final int CPUS = Math.max(2, Runtime.getRuntime().availableProcessors());

    /**
     * header标签
     */
    public static final String HEADER_SOURCE = "source";
    public static final String HEADER_ACCOUNT_ID = "account-id";
    public static final String HEADER_SUPPLIER_ID = "supplier-id";

    /**
     * redis的key与value的序列化类型
     */
    public static final String REDIS_SERIALIZE_JACKSON = "jackson";
    public static final String REDIS_SERIALIZE_FASTJSON = "fastjson";
    public static final String REDIS_SERIALIZE_PROTOBUF = "protobuf";

    /**
     * 静态构造函数
     */
    static {
        // 对象序列化时, 值为null的属性也序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 反序列化对象时, 忽略对象没有的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 日期默认格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(CoreConstants.DATETIME_FORMAT));
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.registerModule(new ParameterNamesModule());
        OBJECT_MAPPER.registerModule(new Jdk8Module());
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        // 对象序列化时, 值为null的属性也序列化
        OBJECT_MAPPER_WITH_TYPE.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 反序列化对象时, 忽略对象没有的属性
        OBJECT_MAPPER_WITH_TYPE.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 日期默认格式
        OBJECT_MAPPER_WITH_TYPE.setDateFormat(new SimpleDateFormat(CoreConstants.DATETIME_FORMAT));
        //如果是空对象的时候,不抛异常
        OBJECT_MAPPER_WITH_TYPE.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 动态类型转换
        OBJECT_MAPPER_WITH_TYPE.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);

        OBJECT_MAPPER_WITH_TYPE.registerModule(new ParameterNamesModule());
        OBJECT_MAPPER_WITH_TYPE.registerModule(new Jdk8Module());
        OBJECT_MAPPER_WITH_TYPE.registerModule(new JavaTimeModule());
    }
}