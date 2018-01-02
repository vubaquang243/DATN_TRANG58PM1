package com.seatech.framework.exception;


import java.util.HashMap;

public class TPCPException extends Exception {
    private String errorKey;
    private String errorParam0;
    private String errorParam1;
    private String errorParam2;
    private String message;    

    public TPCPException() {
        super();
    }
    public TPCPException(String message) {
        this.message = message;
    }
    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorParam0(String errorParam0) {
        this.errorParam0 = errorParam0;
    }

    public String getErrorParam0() {
        return errorParam0;
    }

    public void setErrorParam1(String errorParam1) {
        this.errorParam1 = errorParam1;
    }

    public String getErrorParam1() {
        return errorParam1;
    }

    public void setErrorParam2(String errorParam2) {
        this.errorParam2 = errorParam2;
    }

    public String getErrorParam2() {
        return errorParam2;
    }
    public static TPCPException createException(String errorKey) {
        TPCPException ex = new TPCPException();
        ex.setErrorKey(errorKey);
        ex.setErrorParam0(null);
        ex.setErrorParam1(null);
        ex.setErrorParam2(null);

        return ex;
    }
    public static TPCPException createException(String errorKey, String errorParam0) {
        TPCPException ex = new TPCPException();
        ex.setErrorKey(errorKey);
        ex.setErrorParam0(errorParam0);
        ex.setErrorParam1(null);
        ex.setErrorParam2(null);

        return ex;
    }
    public static TPCPException createException(String errorKey, String errorParam0, String errorParam1) {
        TPCPException ex = new TPCPException();
        ex.setErrorKey(errorKey);
        ex.setErrorParam0(errorParam0);
        ex.setErrorParam1(errorParam1);
        ex.setErrorParam2(null);

        return ex;
    }
    public static TPCPException createException(String errorKey, String errorParam0, String errorParam1, String errorParam2) {
        TPCPException ex = new TPCPException();
        ex.setErrorKey(errorKey);
        ex.setErrorParam0(errorParam0);
        ex.setErrorParam1(errorParam1);
        ex.setErrorParam2(errorParam2);

        return ex;
    }
    public String getMessage(HashMap dmucLoi) {
        String moTaLoi = (String) dmucLoi.get(errorKey);
        if (null != errorParam2 && null != errorParam1 && null != errorParam0){
            moTaLoi = moTaLoi.replace("{0}", errorParam0);
            moTaLoi = moTaLoi.replace("{1}", errorParam1);
            moTaLoi = moTaLoi.replace("{2}", errorParam2);
        } else if (null == errorParam2 && null != errorParam1 && null != errorParam0){
            moTaLoi = moTaLoi.replace("{0}", errorParam0);
            moTaLoi = moTaLoi.replace("{1}", errorParam1);
        } else if (null == errorParam2 && null == errorParam1 && null != errorParam0){
            moTaLoi = moTaLoi.replace("{0}", errorParam0);
        }
        return moTaLoi;
    }
    public String getMoTaLoi(HashMap dmucLoi){
      return (String) dmucLoi.get(errorKey);
    }
    public String getMessage() {
        return message;
    }
}

