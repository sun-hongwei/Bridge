package com.bridge.system.service;

import java.util.List;
import com.bridge.system.domain.SysUser;

/**
 * 用户 业务层
 * 
 * @author bridge
 */
public interface ISysUserService
{
    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId) throws Exception;



    /**
     * 修改用户详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserInfo(SysUser user);

}
