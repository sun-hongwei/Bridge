package com.bridge.web.controller.tool;

import com.bridge.common.base.Business;
import com.bridge.common.json.JSON;
import com.bridge.common.utils.StringUtils;
import com.bridge.common.utils.http.HttpClientUtil;
import com.bridge.system.api.BaseAPI;
import com.bridge.system.domain.ManuFroms;
import com.bridge.system.domain.Staff;
import com.bridge.system.domain.SysUser;
import com.bridge.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * swagger 接口
 *
 * @author bridge
 */
@Controller
@Api(value = "安卓 APP接口", description = "APP 端生产接口")
public class SwaggerController {

    private static final Logger log = LoggerFactory.getLogger(SwaggerController.class);

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息接口")
    @RequestMapping(method = RequestMethod.GET, value = "/base/{userId}/getUser")
    @ResponseBody
    public Business<SysUser> getUser(@PathVariable("userId") Long userId) {

        log.info("用户ID为" + userId);

        //定义业务 json返回对象
        Business<SysUser> userBusiness = new Business<>();

        SysUser sysUser = null;

        try {
            sysUser = sysUserService.selectUserById(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sysUser != null) {
            userBusiness.setData(sysUser);
        } else {
            userBusiness.setCode(404);
            userBusiness.setMessage("未查询到数据");
        }
        return userBusiness;
    }

    @ApiOperation(value = "获取E9系统信息", notes = "查询工长接口")
    @RequestMapping(method = RequestMethod.GET, value = "/make/getStaffNames")
    @ResponseBody
    public Business<Staff> getStaffNames() {
        Business<Staff> staffBusiness = new Business<>();
        //获取工长接口
        String httpPost = HttpClientUtil.sendHttpPost(BaseAPI.getStaffNames);

        if (StringUtils.isNotEmpty(httpPost) && !httpPost.contains("HTTP Status 404")) {

            try {
                Staff staff = JSON.unmarshal(httpPost, Staff.class);

                if (staff != null && !staff.getResult().equals("error")) {
                    staffBusiness.setData(staff);
                } else {
                    staffBusiness.setCode(500);
                    staffBusiness.setMessage("查询工长信息错误");
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                staffBusiness.setCode(500);
                staffBusiness.setMessage("查询工长信息错误");
                e.printStackTrace();
            }
        }
        return staffBusiness;
    }

    /**
     *
     * @param facilityCode 设备编号
     * @return
     */
    @ApiOperation(value = "获取E9系统信息", notes = "查询制造通知单接口")
    @RequestMapping(method = RequestMethod.GET, value = "/make/{facilityCode}/getManuFroms")
    @ResponseBody
    public Business<ManuFroms> getManuFroms(@PathVariable("facilityCode") String facilityCode) {

        Business<ManuFroms> manuFromsBusiness = new Business<>();

        try {

            String httpPost = HttpClientUtil.sendHttpPost(BaseAPI.getManuFroms+"?facilityCode="+facilityCode);

            System.out.println(httpPost);

            ManuFroms froms = JSON.unmarshal(httpPost, ManuFroms.class);

            System.out.println(froms.getManuFrom().size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manuFromsBusiness;
    }


}
