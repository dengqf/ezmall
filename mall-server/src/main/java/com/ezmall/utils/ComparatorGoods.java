package com.ezmall.utils;

import com.ezmall.model.Goods;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-10
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class ComparatorGoods implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Goods ca1 = (Goods) o1;
        Goods ca2 = (Goods) o2;
        return ca1.getSellPrice().compareTo(ca2.getSellPrice());
    }
}
