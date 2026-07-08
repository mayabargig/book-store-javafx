package org.hit.common;

import java.io.Serializable;

public class Request implements Serializable {

    private String action;
    private Object data;
    private String algorithmName;

    public Request() {}

    public Request(String action, Object data, String algorithmName) {
        this.action = action;
        this.data = data;
        this.algorithmName = algorithmName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}