/*
 * 类名 GoodsThriftApi.java
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
package com.ezmall.server.api;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.model.Goods;
import com.ezmall.server.api.GoodsServiceApi.Iface;
import com.ezmall.service.IGoodsService;
import com.ezmall.utils.ApplicationContextProvider;

/**
 * Class description goes here.
 *

 * @author tanfeng Jul 9, 2014 7:25:46 PM
 */
@Service
public class GoodsThriftApi implements Iface {

	@Autowired
    IGoodsService goodsService;

	@Override
	public String getStr(String param1) throws TException {
		// TODO Auto-generated method stub
		System.out.println("param:" + param1);
		return "test ok:" + param1;
	}

	@Override
	public String insertGoods(TGoodsDto goods) throws TException {
		System.out.println(goods.getBrandName());
		GoodsDto dto = new GoodsDto();
		Goods gd = new Goods();
        TGoods tg=goods.getGoods();
        gd.setName(tg.getName());
        gd.setSellPrice(tg.getSellPrice());
        gd.setMarketPrice(tg.getMarketPrice());
        gd.setCurrencyType(tg.getCurrencyType());
        gd.setThirdNo(tg.getThirdNo());
        gd.setThirdLink(tg.getThirdLink());
        gd.setGoodsDesc(tg.getGoodsDesc());
        gd.setThirdDomain(tg.getThirdDomain());
        gd.setThirdId(tg.getThirdId());
        gd.setThirdCol1(tg.getThirdCol1());
        gd.setThirdCol2(tg.getThirdCol2());

		dto.setBrandEnglishName(goods.getBrandEnglishName());
		dto.setBrandName(goods.getBrandName());
		dto.setStructName(goods.getStructName());
		dto.setGoods(gd);
        dto.setPropMap(goods.getPropMap());
		
//		IGoodsService goodsService = ApplicationContextProvider.getApplicationContext().getBean("goodsService", IGoodsService.class);
//		System.out.println("goodsservice:" + goodsService);
		ResultDto ret = null;
        try{
            ret= goodsService.importGoods(dto);
        }catch (Exception e){
            System.out.println("抓取数据异常:" + gd.getThirdNo());
            ret=new ResultDto();
            ret.setSuccess(false);
            ret.setMessage(gd.getThirdNo()+"抓取数据异常:" + e.getMessage());
        }

		return ret.getMessage();
	}


	@Override
	public boolean isGoodExists(String thirdNo, String thridDomain)
			throws TException {
		// TODO Auto-generated method stub
		return goodsService.isGoodImport(thirdNo,thridDomain)!=null;
	}

	@Override
	public boolean checkThirdId(String thirdId, String thridDomain)
			throws TException {
        return goodsService.isGoodImportByThirdId(thirdId,thridDomain)!=null;
	}



}
