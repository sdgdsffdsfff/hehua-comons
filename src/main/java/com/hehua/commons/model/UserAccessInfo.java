package com.hehua.commons.model;

import java.util.Date;

/**
 * Created by liuweiwei on 14-9-4.
 * 用户请求信息上下文
 */
public class UserAccessInfo {

    /* 请求时间 */
    private String tm;
    /* app版本 */
    private String appid;
    /* 渠道id */
    private String channelid;
    /* 会话id */
    private String traceid;
    /* 妈妈状态 */
    private int preganancy;
    /* 宝宝性别 */
    private int babyGender;
    /* 宝宝生日 */
    private String babyBirthday;
    /* 宝宝预产期 */
    private String edc;
    /* 用户id */
    private long uid;
    /* 设别id */
    private String clientid;
    /* 事件 */
    private String event;
    /* 服务器处理结果 */
    private int status;
    /* 操作系统 */
    private String os;
    /* 设备模型 */
    private String dm;
    /* ip */
    private String ip;
    /* 商品id */
    private long itemid;
    /* skuid */
    private String skuid;
    /* 购物车商品数量 */
    private int cartSize;
    /* 购物车商品选择数量*/
    private int cartSelected;
    /* 商品类别数量 */
    private int itemQuantity;
    /* sku总数 */
    private int skuQuantity;
    /* 订单id*/
    private long orderid;
    /* 商品总价 */
    private double totolFee;
    /* 运费 */
    private double deliveryFee;

    private int groupid;

    public static final int STATUS_SUCCESS = 0;

    @Override
    public String toString() {
        return tm + '|' +
                (appid == null ? "-" : appid) + '|' +
                (channelid == null ? "-" : channelid) + '|' +
                (traceid == null ? "-" : traceid) + '|' +
                preganancy + '|' +
                babyGender + '|' +
                (babyBirthday == null || babyBirthday.equals("") ? "-" : babyBirthday) + '|' +
                (edc == null || edc.equals("") ? "-" : edc) + '|' +
                uid + '|' +
                (clientid == null ? "-" : clientid) + '|' +
                event + '|' +
                status + '|' +
                groupid + '|' +
                (os == null ? "-" : os) + '|' +
                (dm == null ? "-" : dm) + '|' +
                (ip == null ? "-" : ip) + '|' +
                (itemid == 0 ? "-" : itemid) + '|' +
                (skuid == null || skuid == "" ? "-" : skuid) + '|' +
                (cartSize == 0 ? "-" : cartSize) + '|' +
                (cartSelected == 0 ? "-" : cartSelected) + '|' +
                (itemQuantity == 0 ? "-" : itemQuantity) + '|' +
                (skuQuantity == 0 ? "-" : skuQuantity) + '|' +
                (orderid == 0 ? "-" : orderid) + '|' +
                (totolFee == 0 ? "-" : totolFee) + '|' +
                (deliveryFee == 0 ? "-" : deliveryFee);
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getTraceid() {
        return traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public int getPreganancy() {
        return preganancy;
    }

    public void setPreganancy(int preganancy) {
        this.preganancy = preganancy;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public int getCartSize() {
        return cartSize;
    }

    public void setCartSize(int cartSize) {
        this.cartSize = cartSize;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getCartSelected() {
        return cartSelected;
    }

    public void setCartSelected(int cartSelected) {
        this.cartSelected = cartSelected;
    }

    public int getSkuQuantity() {
        return skuQuantity;
    }

    public void setSkuQuantity(int skuQuantity) {
        this.skuQuantity = skuQuantity;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public double getTotolFee() {
        return totolFee;
    }

    public void setTotolFee(double totolFee) {
        this.totolFee = totolFee;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getBabyGender() {
        return babyGender;
    }

    public void setBabyGender(int babyGender) {
        this.babyGender = babyGender;
    }

    public String getBabyBirthday() {
        return babyBirthday;
    }

    public void setBabyBirthday(String babyBirthday) {
        this.babyBirthday = babyBirthday;
    }

    public String getEdc() {
        return edc;
    }

    public void setEdc(String edc) {
        this.edc = edc;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
}
