package com.example.testBootOne.entity;

/**
 * 状态码
 */
public class StatusCode {
    /**
     * 操作成功
     */
    public static final String OK = "200";
    /**
     * 失败
     */
    public static final String ERROR = "201";
    /**
     * 用户名或密码错误
     */
    public static final String LOGINERROR = "202";
    /**
     * token过期
     */
    public static final String TOKENEXPIREE = "203";
    /**
     * 权限不足
     */
    public static final String ACCESSERROR = "403";
    /**
     * 远程调用失败
     */
    public static final String REMOTEERROR = "204";
    /**
     * 重复操作
     */
    public static final String REPERROR = "205";
    /**
     * 业务层错误
     */
    public static final String SERVICEERROR = "500";
    /**
     * 资源不存在
     */
    public static final String NOTFOUND = "404";
}
