package com.gzy.someapis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "YOUONE", schema = "someapis", catalog = "")
public class YouoneEntity {
    private int id;
    private String imgUrl;
    private String textNum;
    private String imgAuther;
    private String textContent;
    private String mon;
    private String day;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "textNum")
    public String getTextNum() {
        return textNum;
    }

    public void setTextNum(String textNum) {
        this.textNum = textNum;
    }

    @Basic
    @Column(name = "imgAuther")
    public String getImgAuther() {
        return imgAuther;
    }

    public void setImgAuther(String imgAuther) {
        this.imgAuther = imgAuther;
    }

    @Basic
    @Column(name = "textContent")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @Basic
    @Column(name = "mon")
    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    @Basic
    @Column(name = "day")
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YouoneEntity that = (YouoneEntity) o;
        return id == that.id &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Objects.equals(textNum, that.textNum) &&
                Objects.equals(imgAuther, that.imgAuther) &&
                Objects.equals(textContent, that.textContent) &&
                Objects.equals(mon, that.mon) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imgUrl, textNum, imgAuther, textContent, mon, day);
    }
}
