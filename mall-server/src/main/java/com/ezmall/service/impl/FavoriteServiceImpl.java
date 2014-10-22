package com.ezmall.service.impl;

import com.ezmall.mapper.FavoriteMapper;
import com.ezmall.model.Favorite;
import com.ezmall.model.FavoriteExample;
import com.ezmall.service.IFavoriteService;
import com.ezmall.utils.CommonUtil;
import com.ezmall.utils.UUIDUtil;
import com.ezmall.vo.FavoriteVo;
import com.ezmall.vo.PageData;
import com.ezmall.vo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-26
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FavoriteServiceImpl implements IFavoriteService {

    @Autowired
    FavoriteMapper favoriteMapper;

    @Override
    public Integer insert(Favorite favorite) {
        return favoriteMapper.insert(favorite);
    }

    @Override
    public Integer insert(String username, String goodsNo) {
        FavoriteExample example = new FavoriteExample();
        example.or().andUserNameEqualTo(username).andGoodsNoEqualTo(goodsNo);
        Favorite favorite = CommonUtil.getFirstObject(favoriteMapper.selectByExample(example));
        Integer result = null;
        if (favorite == null) {
            favorite = new Favorite();
            favorite.setId(UUIDUtil.getUUID());
            favorite.setCreateDate(new Date());
            favorite.setGoodsNo(goodsNo);
            favorite.setUserName(username);
            insert(favorite);
        } else {
            favorite.setCreateDate(new Date());
            save(favorite);
        }
        return result;
    }

    @Override
    public Integer save(Favorite favorite) {
        return favoriteMapper.updateByPrimaryKeySelective(favorite);
    }

    @Override
    public Integer delete(String id) {
        return favoriteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer delete(List<String> id) {
        FavoriteExample example = new FavoriteExample();
        example.or().andIdIn(id);
        return favoriteMapper.deleteByExample(example);
    }

    @Override
    public PageData<FavoriteVo> getFavoritePageData(String username, Query query) {
        PageData<FavoriteVo> pageData = null;
        Integer rows = favoriteMapper.countMemberFavoriteVos(username);
        if (rows < 1) {
            pageData = new PageData<FavoriteVo>(1, 0);
        } else {
            pageData = new PageData<FavoriteVo>(query.getPage(), query.getPageSize(), rows);
            List<FavoriteVo> list = favoriteMapper.getMemberFavoriteVos(username, CommonUtil.getRowBounds(query));
            pageData.setData(list);
        }
        return pageData;
    }
}
