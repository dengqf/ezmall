package com.ezmall.model;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-3
 * Time: 下午10:29
 * To change this template use File | Settings | File Templates.
 */
public class Area {
    String country;
    String city;
    String mall;
    String currencyType;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
}
