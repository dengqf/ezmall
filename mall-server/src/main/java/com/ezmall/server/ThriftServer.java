/*
 * 类名 ThriftServer.java
 *
 * 版本信息 
 *
 * 日期 Jul 9, 2014
 *
 * 版权声明Copyright (C) 2014 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.ezmall.server;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.ezmall.server.api.GoodsServiceApi;
import com.ezmall.server.api.GoodsThriftApi;

public class ThriftServer {

  public static GoodsThriftApi handler;

  public static GoodsServiceApi.Processor processor;

  public static void main(String [] args) {
    try {
//    	GoodsThriftApi api = new GoodsThriftApi();
//    	GoodsServiceApi.Processor<GoodsThriftApi> gs = new GoodsServiceApi.Processor<GoodsThriftApi>(api);
    	
      handler = new GoodsThriftApi();
      processor = new GoodsServiceApi.Processor(handler);

//      Runnable simple = new Runnable() {
//        public void run() {
//          simple(processor);
//        }
//      };      
//      Runnable secure = new Runnable() {
//        public void run() {
//          secure(processor);
//        }
//      };

//      new Thread(simple).start();
//      new Thread(secure).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void simple() {
	  System.out.println("-------------start thrift server...");
    try {
    	
    	handler = new GoodsThriftApi();
        processor = new GoodsServiceApi.Processor(handler);
      TServerTransport serverTransport = new TServerSocket(9090);
//      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      // Use this for a multithreaded server
      // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

      TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
  	  tArgs.maxWorkerThreads(100);
  	  tArgs.minWorkerThreads(20);
  	  tArgs.processor(processor);
  	  tArgs.protocolFactory(new TBinaryProtocol.Factory());
  	
//      System.out.println("Starting the simple server...");
//      server.serve();
  	  
  	TServer tServer = new TThreadPoolServer(tArgs);
	System.out.println("......Thrift Server Service Start......"); 
	tServer.serve();
	serverTransport.close();
	tServer.stop();
	System.out.println("......Thrift Server Service Stop......");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void secure(GoodsServiceApi.Processor processor) {
    try {
      /*
       * Use TSSLTransportParameters to setup the required SSL parameters. In this example
       * we are setting the keystore and the keystore password. Other things like algorithms,
       * cipher suites, client auth etc can be set. 
       */
      TSSLTransportParameters params = new TSSLTransportParameters();
      // The Keystore contains the private key
      params.setKeyStore("../../lib/java/test/.keystore", "thrift", null, null);

      /*
       * Use any of the TSSLTransportFactory to get a server transport with the appropriate
       * SSL configuration. You can use the default settings if properties are set in the command line.
       * Ex: -Djavax.net.ssl.keyStore=.keystore and -Djavax.net.ssl.keyStorePassword=thrift
       * 
       * Note: You need not explicitly call open(). The underlying server socket is bound on return
       * from the factory class. 
       */
      TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, params);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      // Use this for a multi threaded server
      // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

      System.out.println("Starting the secure server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
/*
public static void startThreadPoolServer(){
	RecommendService recomService = new RecommendService();
	TRecommendService.Processor<RecommendService> tp = new TRecommendService.Processor<RecommendService>(recomService);
	
	TServerTransport serverTransport = null;
	try {
		serverTransport = new TServerSocket(ConstConf.server_port);
	} catch (TTransportException e) {
		e.printStackTrace();
	}
	
	TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
	tArgs.maxWorkerThreads(ConstConf.maxRequestNum);
	tArgs.minWorkerThreads(20);
	tArgs.processor(tp);
//	tArgs.protocolFactory(new TCompactProtocol.Factory());
	tArgs.protocolFactory(new TBinaryProtocol.Factory());
	
	TServer tServer = new TThreadPoolServer(tArgs);
	System.out.println(getCurDateTime()+"......Recommend Server Service 2.0.0 Start......"); 
	tServer.serve();
	serverTransport.close();
	tServer.stop();
	System.out.println("......Recommend Server Service 2.0.0 Service Stop......");
}
*/