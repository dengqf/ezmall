package com.ezmall.apiImpl;

import com.ezmall.api.IGoodsBaseApi;
import com.ezmall.dto.GoodsDto;
import com.ezmall.dto.ResultDto;
import com.ezmall.model.*;
import com.ezmall.service.*;
import com.ezmall.utils.CommonConstrant;
import com.ezmall.utils.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午11:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GoodsBaseApiImpl implements IGoodsBaseApi {

    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsPriceService goodsPriceService;

    @Override
    public ResultDto importGoods(GoodsDto dto) {
        return goodsService.importGoods(dto);
    }



    @Override
    public String getStr(String str) {
        System.out.print("接收的参数为:" + str);
        return str;
    }
}
