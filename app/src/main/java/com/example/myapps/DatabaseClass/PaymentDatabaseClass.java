package com.example.myapps.DatabaseClass;

public class PaymentDatabaseClass {
    private double _1midpayment;
    private double _1endpayment;
    private double _2midpayment;
    private double _2endpayment;
    private double _3midpayment;
    private double _3endpayment;
    private double _4midpayment;
    private double _4endpayment;
    private String _Uid;
    private double _Balance;
    private  double _Zsukli;
    private double _Zkulang;

    public PaymentDatabaseClass() {
    }

    public PaymentDatabaseClass(double _1midpayment, double _1endpayment, double _2midpayment, double _2endpayment, double _3midpayment, double _3endpayment, double _4midpayment, double _4endpayment, String _Uid, double _Balance, double _Zsukli, double _Zkulang) {
        this._1midpayment = _1midpayment;
        this._1endpayment = _1endpayment;
        this._2midpayment = _2midpayment;
        this._2endpayment = _2endpayment;
        this._3midpayment = _3midpayment;
        this._3endpayment = _3endpayment;
        this._4midpayment = _4midpayment;
        this._4endpayment = _4endpayment;
        this._Uid = _Uid;
        this._Balance = _Balance;
        this._Zsukli = _Zsukli;
        this._Zkulang = _Zkulang;
    }

    public double get_1midpayment() {
        return _1midpayment;
    }

    public double get_1endpayment() {
        return _1endpayment;
    }

    public double get_2midpayment() {
        return _2midpayment;
    }

    public double get_2endpayment() {
        return _2endpayment;
    }

    public double get_3midpayment() {
        return _3midpayment;
    }

    public double get_3endpayment() {
        return _3endpayment;
    }

    public double get_4midpayment() {
        return _4midpayment;
    }

    public double get_4endpayment() {
        return _4endpayment;
    }

    public String get_Uid() {
        return _Uid;
    }

    public double get_Balance() {
        return _Balance;
    }

    public double get_Zsukli() {
        return _Zsukli;
    }

    public double get_Zkulang() {
        return _Zkulang;
    }
}
