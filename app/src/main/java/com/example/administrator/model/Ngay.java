package com.example.administrator.model;

public class Ngay {
    private String ngay;
    private String trangthai;
    private String nhietdoMin;
    private String nhietdoMax;
    private String icon;

    public Ngay() {
    }

    public Ngay(String ngay, String trangthai, String nhietdoMin, String nhietdoMax, String icon) {
        this.ngay = ngay;
        this.trangthai = trangthai;
        this.nhietdoMin = nhietdoMin;
        this.nhietdoMax = nhietdoMax;
        this.icon = icon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNhietdoMin() {
        return nhietdoMin;
    }

    public void setNhietdoMin(String nhietdoMin) {
        this.nhietdoMin = nhietdoMin;
    }

    public String getNhietdoMax() {
        return nhietdoMax;
    }

    public void setNhietdoMax(String nhietdoMax) {
        this.nhietdoMax = nhietdoMax;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
