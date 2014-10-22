package com.ezmall.service;

import com.ezmall.model.PropItem;
import com.ezmall.vo.PageData;
import com.ezmall.vo.PropItemQueryVo;
import com.ezmall.vo.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-10
 * Time: 下午11:42
 * To change this template use File | Settings | File Templates.
 */
public interface IPropItemService {

   Integer insert(PropItem item);

   Integer insert(PropItem item,String categoryNo);

   Integer save(PropItem item);
    PropItem getPropItemById(String id);

    PropItem getPropItemByNo(String no);

    PropItem getPropItemByName(String name);

    List<PropItem> getAllPropItems();

    PageData<PropItem> getPropItemPageData(PropItemQueryVo vo,Query query);

    List<PropItem> getPropItemListByCategoryNo(String categoryNo);

    Integer delPropItemByNo(PropItem item);
}
