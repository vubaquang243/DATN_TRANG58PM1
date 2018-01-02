package com.seatech.framework.datamanager;

public class BaseVO extends BaseObject{
    public BaseVO() {
        super();
    }
    private String guid;

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }
}
