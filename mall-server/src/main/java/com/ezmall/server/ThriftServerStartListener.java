/*
 * 类名 ThriftServerStartListener.java
 *
 * 版本信息 
 *
 * 日期 Jul 10, 2014
 *
 * 版权声明Copyright (C) 2014 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.ezmall.server;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Class description goes here.
 *
 * @version  
 * @author tanfeng Jul 10, 2014 6:00:46 PM
 */
public class ThriftServerStartListener implements ServletContextListener{  
      
  
    @Override  
    public void contextDestroyed(ServletContextEvent event) {  
          
    }  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public void contextInitialized(ServletContextEvent event) {  
        //启动SETUP SEERVER  
        try {  
            ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
              
            List<ThriftServerProxy> list = ((List<ThriftServerProxy>) context.getBean("thriftServerList"));  
            if(list!=null&&list.size()>0){  
                for(ThriftServerProxy userProxy:list){  
                    userProxy.start();  
                }  
            }  
  
            System.out.println("Thrift Server Starting ..................");  
        } catch (Exception e) {  
            System.out.println("Thrift Server Start Error:" + e);  
            e.printStackTrace();  
        }  
    }  
  
}  