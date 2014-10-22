/*
 * 类名 ThriftServerProxy.java
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

import java.lang.reflect.Constructor;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Class description goes here.
 * 
 * @version
 * @author tanfeng Jul 10, 2014 5:50:47 PM
 */
@Service
public class ThriftServerProxy {
	
	/**
	 * slf4j def
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ThriftServerProxy.class);

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public void start() {
//		new Thread() {
//			public void run() {
//
//				try {
//					TServerSocket serverTransport = new TServerSocket(9191);
//
//					// Class Processor =
//					// Class.forName(getServiceInterface()+"$Processor");
//					// Class Iface =
//					// Class.forName(getServiceInterface()+"$Iface");
//
//					GoodsThriftApi handler = new GoodsThriftApi();
//					GoodsServiceApi.Processor processor = new GoodsServiceApi.Processor(
//							handler);
//
//					// Constructor con = Processor.getConstructor(Iface);
//					// TProcessor processor =
//					// (TProcessor)con.newInstance(serviceImplObject);
//
//					Factory protFactory = new TBinaryProtocol.Factory(true,
//							true);
//					TThreadPoolServer.Args args = new TThreadPoolServer.Args(
//							serverTransport);
//					args.protocolFactory(protFactory);
//
//					args.processor(processor);
//					TServer server = new TThreadPoolServer(args);
//					System.out.println("Starting server on port 9191 ...");
//					server.serve();
//
//				} catch (TTransportException e) {
//					e.printStackTrace();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}.start();
//	}
	
	private int port;// 端口
	private String serviceInterface;// 实现类接口
	
	private Object serviceImplObject;//实现类
	
	private String serviceIface;//接口

	public Object getServiceImplObject() {
		return serviceImplObject;
	}

	public void setServiceImplObject(Object serviceImplObject) {
		this.serviceImplObject = serviceImplObject;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	

	public String getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(String serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	
	public String getServiceIface() {
		return serviceIface;
	}

	public void setServiceIface(String serviceIface) {
		this.serviceIface = serviceIface;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start() {
		new Thread() {
			public void run() {

				try {
					TServerSocket serverTransport = new TServerSocket(getPort());					
					Class Processor = Class.forName(getServiceInterface()+"$Processor");
					Class Iface = Class.forName(getServiceInterface()+"$Iface");    
					
					
					Constructor con = Processor.getConstructor(Iface);
					TProcessor processor = (TProcessor)con.newInstance(serviceImplObject);

					TProtocolFactory protFactory = new TBinaryProtocol.Factory(true,true);
					//TProtocolFactory protFactory = new TCompactProtocol.Factory(1024);
					TThreadPoolServer.Args args = new TThreadPoolServer.Args(
							serverTransport);
					args.protocolFactory(protFactory);
					
					args.processor(processor);
					TServer server = new TThreadPoolServer(args);
					logger.info("Starting server on port "+getPort()+" ...");
					server.serve();

				} catch (TTransportException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
