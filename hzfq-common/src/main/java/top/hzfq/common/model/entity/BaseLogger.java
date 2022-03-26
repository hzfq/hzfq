package top.hzfq.common.model.entity;

import java.sql.Timestamp;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/2/27 22:08
 */
public class BaseLogger {

    private Long logId;
    private String logLevel; //日志级别
    private String appId; //应用
    private String appModule; //模块
    private String appAction; //功能
    private String userId;
    private String clientIp;
    private String serverIp;
    private String serverPort;
    private String httpUrl;
    private String httpMethod;
    private String controllerClass;
    private String controllerMethod;
    private String params;
    private String result;
    private String exceptionClass;
    private String exceptionMsg;
    private String exceptionStack;
    private Integer costTime;
    private Timestamp createTime;

}
