package com.bridge.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridge.system.domain.SysUser;
import com.bridge.system.mapper.SysUserMapper;
import com.bridge.system.service.ISysUserService;

/**
 * 用户 业务层处理
 * 
 * @author bridge
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) throws Exception
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user)
    {
        return userMapper.updateUser(user);
    }

}
