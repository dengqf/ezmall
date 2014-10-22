package com.ezmall.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-20
 * Time: 下午10:15
 * To change this template use File | Settings | File Templates.
 */
public class PropItemQueryVo {
    String name;
    List<String> propItemNos;
    String firstCategory;
    String secondCategory;
    String thirdCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPropItemNos() {
        return propItemNos;
    }

    public void setPropItemNos(List<String> propItemNos) {
        this.propItemNos = propItemNos;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public String getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        this.thirdCategory = thirdCategory;
    }
}
