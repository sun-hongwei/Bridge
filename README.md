##E9-Bridge对接平台简介

Bridge平台利用 springBoot搭建，为E9产品对接提供服务

##注意事项
1、部署服务及 log 路径配置   
   
    配置文件目录查找方法 Tomcat 解压 bridge-admin.war 后打开 bridge-admin文件夹 WEB-INF目录 classes目录下。
    1、服务端 IP 端口号配置在 application.yml文件 "E9:" 根节点下，按照注释配置即可。
    2、按照实际需求修改 logback.xml文件下 log.path 日志路径。
    3、在数据库执行 sql目录下quartz.sql 具体数据库配置在application-druid.yml 配置。
   
2、Tomcat 部署加载两次项目问题   
   
    1.修改Tomcat目录下 conf 下的 server.xml
    
    配置形式如下： 
    <Host name="localhost"  appBase=""
                unpackWARs="true" autoDeploy="true">
    
    <Context path="/" docBase="/Users/hongweisun/App/apache-tomcat-8.5.35-ejkj/webroot/bridge-admin" debug="0"/>
    
    操作步骤：
    1）、原有appBase清空。
    2）、增加<Context path="/" docBase="/Users/hongweisun/App/apache-tomcat-8.5.35-ejkj/webroot/bridge-admin" debug="0"/>docBase 按照服务器实际情况配置
    3）、新建webroot目录。
    4）、将bridge-admin.war 拷贝到docBase目录。
    
    