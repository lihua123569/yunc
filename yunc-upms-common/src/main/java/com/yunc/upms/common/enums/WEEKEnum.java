package com.yunc.upms.common.enums;
public enum WEEKEnum{  
    WEEK_0("", "请选择"),
    WEEK_1("1", "星期一"),
    WEEK_2("2", "星期二"),
    WEEK_3("3", "星期三"),
    WEEK_4("4", "星期四"),
    WEEK_5("5", "星期五"),
    WEEK_6("6", "星期六"),
    WEEK_7("7", "星期天");
    private String key;  
    private String value;  
    //自定义的构造函数，参数数量，名字随便自己取  
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用   
    private WEEKEnum(String key, String value)  
    {  
        this.key = key;  
        this.value = value;  
    }  
  
    public String getKey()  
    {  
        return key;  
    }  
  
    public void setKey(String key)  
    {  
        this.key = key;  
    }  
  
    public String getValue()  
    {  
        return value;  
    }  
  
    public void setValue(String value)  
    {  
        this.value = value;  
    }  
    //重新toString方法，默认的toString方法返回的就是枚举变量的名字，和name()方法返回值一样  
    @Override  
    public String toString()  
    {  
        return this.key+":"+this.value;  
          
    }  
  
}  