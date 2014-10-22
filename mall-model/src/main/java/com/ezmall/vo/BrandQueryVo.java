package com.ezmall.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-20
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public class BrandQueryVo {
    private String name;
    private String englishName;
    private List<String> brandNos;

    private String firstCategory;
    private String secondCategory;
    private String thirdCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public List<String> getBrandNos() {
        return brandNos;
    }

    public void setBrandNos(List<String> brandNos) {
        this.brandNos = brandNos;
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
