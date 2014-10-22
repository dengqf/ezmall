package com.ezmall.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;
	 
    public static ApplicationContext getApplicationContext() {
    	System.out.println("context = " + context);
        return context;
    }
 
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
    	System.out.println("ctx = " + ctx);
        context = ctx;
    }

}
