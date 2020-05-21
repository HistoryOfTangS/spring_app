/*
 * Copyright (c) 2014-2020 杭州派迩信息技术有限公司 All Rights Reserved.
 * ProjectName: pi-cloud
 * ModuleName: picloud-common-core
 * FileName: LoggerManager.java
 * Author: YuZheng
 * Date: 2020/04/02 16:14:02
 * Version: 1.0
 * LastModified: 2020/04/01 15:36:01
 */

package com.tangyiner.world.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuzheng
 * @create 2018-07-17 15:09
 */

public class LoggerManager {

    private static final Logger runLog = LoggerFactory.getLogger("RUNLOG");
    private static final Logger kpiLog = LoggerFactory.getLogger("KPILOG");
    private static final Logger eventBusLog = LoggerFactory.getLogger("EVENTBUSLOG");
    private static final Logger webSocketLog = LoggerFactory.getLogger("WEBSOCKETLOG");
    private static final Logger scheduleTaskLog = LoggerFactory.getLogger("SCHEDULETASKLOG");
    private static final Logger messageLog = LoggerFactory.getLogger("MESSAGELOG");
    private static final Logger orLog = LoggerFactory.getLogger("ORLOG");
    private static final Logger sqlLog = LoggerFactory.getLogger("SQLLOG");

    public static Logger getRunLog() {
        return runLog;
    }

    public static Logger getKpiLog() {
        return kpiLog;
    }

    public static Logger getEventBusLog() {
        return eventBusLog;
    }

    public static Logger getWebSocketLog() {
        return webSocketLog;
    }

    public static Logger getScheduleTaskLog() {
        return scheduleTaskLog;
    }

    public static Logger getMessageLog() {
        return messageLog;
    }

    public static Logger getOrLog() {
        return orLog;
    }

    public static Logger getSqlLog() {return sqlLog; }

}
