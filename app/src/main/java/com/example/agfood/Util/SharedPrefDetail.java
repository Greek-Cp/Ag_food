package com.example.agfood.Util;

public class SharedPrefDetail {
    private String nameKey, nameSharedPref;
    private String serializeDataList;

    public SharedPrefDetail(String nameKey, String nameSharedPref, String serializeDataList) {
        this.nameKey = nameKey;
        this.nameSharedPref = nameSharedPref;
        this.serializeDataList = serializeDataList;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getNameSharedPref() {
        return nameSharedPref;
    }

    public void setNameSharedPref(String nameSharedPref) {
        this.nameSharedPref = nameSharedPref;
    }

    public String getSerializeDataList() {
        return serializeDataList;
    }

    public void setSerializeDataList(String serializeDataList) {
        this.serializeDataList = serializeDataList;
    }
}
