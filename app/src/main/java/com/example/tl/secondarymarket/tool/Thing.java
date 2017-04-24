package com.example.tl.secondarymarket.tool;

/**
 * Created by gesangdianzi on 2017/3/12.
 */
public class Thing {

    private String name;
    private String value;
    private String time;
    private int image;

    public Thing(int image,String name,String time,String value){
        this.image=image;
        this.name=name;
        this.time=time;
        this.value=value;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getValue() {
        return value;
    }
}
