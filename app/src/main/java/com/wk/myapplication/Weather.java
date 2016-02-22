package com.wk.myapplication;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-01-25 14:53
 */
public class Weather{

    private String TAG = getClass().getSimpleName();

    private String name;
    private String iconUrl;

    public Weather(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "Weather{" +
            "name='" + name + '\'' +
            ", iconUrl='" + iconUrl + '\'' +
            '}';
    }
}
