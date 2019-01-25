##E9-Bridge对接平台简介

Bridge平台利用 springBoot搭建，为E9产品对接提供服务

##注意事项
Tomcat 部署加载两次项目问题   
   
    1.修改Tomcat目录下 conf 下的 server.xml
    
    配置形式如下： 
    <Host name="localhost"  appBase=""
                unpackWARs="true" autoDeploy="true">
    
    <Context path="/" docBase="/Users/hongweisun/App/apache-tomcat-8.5.35-ejkj/webroot/bridge-admin" debug="0"/>
    
    操作步骤：
    1）、原有appBase清空。
    2）、增加<Context path="/" docBase="/Users/hongweisun/App/apache-tomcat-8.5.35-ejkj/webroot/bridge-admin" debug="0"/>配置
    3）、新建webroot目录。
    4）、bridge-admin 为bridge-admin.war
    
    