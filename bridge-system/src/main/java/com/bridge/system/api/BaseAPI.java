package com.bridge.system.api;

/**
 * app端基础信息接口
 *
 * @author bridge
 */
public interface BaseAPI {

    String baseURL = "slmME/rs/" ;

    /**
     * 获取工长信息接口
     */
    String getStaffNames = baseURL + "userapp/getStaffNames" ;

    /**
     * 获取任务接口
     */
    String getTask = baseURL + "extrusionMakeapp/getJsListMake" ;

    /**
     * 获取制造通知单
     */
    String getManuFroms = baseURL + "makeapp/getManuFroms" ;
}
