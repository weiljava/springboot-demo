package com.example.demo.domain;

import com.example.demo.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dinghw on 2017/7/3.
 */
public class Content implements Serializable {

    private String gcid;
    private String cid;
    private String name;
    private Integer datatype;//10：游戏，20：音乐，30：视频，40：动漫，50：阅读

    private String pic1;//图片1 和数字内容类型相关 10：130*130高清logo图标  20：歌曲图片 30：XX_sc.jpg  40：web图标地址 50：封面URL1
    private String pic2;//图片2 和数字内容类型相关  10:60*60 图标 20：专辑图片 30：XX_HSJ1080H.JPG 40：web大图片地址 50：封面URL2
    private String pic3;//图片3 和数字内容类型相关  10: 安卓门户游戏截图1 20：歌手图片 30：XX_HSJ720H.JPG 40:无  50：封面URL3
    private String pic4;//图片4 和数字内容类型相关 10: 安卓门户游戏截图2 20:无 30:无 40:无 50：封面URL4
    private String pic5;//图片5 和数字内容类型相关 10: 安卓门户游戏截图3 20:无 30:无 40:无 50:无
    private String character1;
    private String character2;


    private String description;//简介
    private String effectiveStartTime;//生效时间
    private String effectiveEndTime;//时效时间
    private Integer price;//价格

    public String getGcid() {
        return gcid;
    }

    public void setGcid(String gcid) {
        this.gcid = gcid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDatatype() {
        return datatype;
    }

    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5;
    }

    public String getCharacter1() {
        return character1;
    }

    public void setCharacter1(String character1) {
        this.character1 = character1;
    }

    public String getCharacter2() {
        return character2;
    }

    public void setCharacter2(String character2) {
        this.character2 = character2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(String effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public String getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(String effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
