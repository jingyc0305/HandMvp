package jing.honngshi.com.videodatapracticefromcibn.category.vod.bean.base;

import java.io.Serializable;

/**
 * Created by JIngYuchun on 2017/10/12.
 */

public class BaseEntity<T> implements Serializable{

    String stateCode;
    String Msg;
    T data;

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getMsg() {
        return Msg;
    }

    public T getData() {
        return data;
    }
}
