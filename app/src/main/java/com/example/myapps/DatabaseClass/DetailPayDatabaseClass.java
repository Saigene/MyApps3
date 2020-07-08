package com.example.myapps.DatabaseClass;

public class DetailPayDatabaseClass {
   private String detailpay;
   private String detaildatepay;
   private String detailstatus;

    public DetailPayDatabaseClass() {
    }

    public DetailPayDatabaseClass(String detailpay, String detaildatepay, String detailstatus) {
        this.detailpay = detailpay;
        this.detaildatepay = detaildatepay;
        this.detailstatus=detailstatus;
    }

    public String getDetailpay() {
        return detailpay;
    }

    public String getDetaildatepay() {
        return detaildatepay;
    }
    public String getDetailstatus(){
        return detailstatus;
    }
}
