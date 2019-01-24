package com.bridge.common.config;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bridge.common.utils.StringUtils;
import com.bridge.common.utils.YamlUtil;

/**
 * 全局配置类
 * 
 * @author bridge
 */
public class Global
{
    private static final Logger log = LoggerFactory.getLogger(Global.class);

    private static String NAME = "application.yml";

    /**
     * 当前对象实例
     */
    private static Global global = null;

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    private Global()
    {
    }

    /**
     * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
     */

    public static synchronized Global getInstance()
    {
        if (global == null)
        {
            synchronized (Global.class)
            {
                if (global == null)
                    global = new Global();
            }
        }
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            Map<?, ?> yamlMap = null;
            try
            {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
            catch (FileNotFoundException e)
            {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取项目名称
     */
    public static String getName()
    {
        return StringUtils.nvl(getConfig("Bridge.name"), "SunHongWei");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion()
    {
        return StringUtils.nvl(getConfig("Bridge.version"), "1.1.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear()
    {
        return StringUtils.nvl(getConfig("Bridge.copyrightYear"), "2019");
    }

    /**
     * 获取E9服务 ip
     */
    public static String getE9Ip()
    {
        return StringUtils.nvl(getConfig("E9.IP"), "0.0.0.0");
    }

    /**
     * 获取E9服务 端口号
     */
    public static String getE9Port()
    {
        return StringUtils.nvl(getConfig("E9.PORT"), "8080");
    }

    /**
     * 获取E9服务 超时时间
     */
    public static String getE9TimeOut()
    {
        return StringUtils.nvl(getConfig("E9.TimeOut"), "3000");
    }
}
