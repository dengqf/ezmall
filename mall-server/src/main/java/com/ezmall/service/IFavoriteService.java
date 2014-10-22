package com.ezmall.service;

import com.ezmall.model.Favorite;
import com.ezmall.vo.FavoriteVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-26
 * Time: 下午10:47
 * To change this template use File | Settings | File Templates.
 */
public interface IFavoriteService {

    Integer insert(Favorite favorite);

    Integer insert(String username,String goodsNo);

    Integer save(Favorite favorite);

    Integer delete(String id);

    Integer delete(List<String> id);



    PageData<FavoriteVo> getFavoritePageData(String username,Query query);




}
