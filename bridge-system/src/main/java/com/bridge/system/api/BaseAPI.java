package com.bridge.system.api;

/**
 * app端基础信息接口
 * @author bridge
 */
public interface BaseAPI {

    /**
     * 获取工长信息接口
     */
    String getStaffNames = "slmME/rs/userapp/getStaffNames";

    /**
     * 获取任务接口
     */
    String getTask = "slmME/rs/extrusionMakeapp/getJsListMake";

    /**
     * 获取制造通知单
     */
    String getManuFroms = "slmME/rs/makeapp/getManuFroms";
}
