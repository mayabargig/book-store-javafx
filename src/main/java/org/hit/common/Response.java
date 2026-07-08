package org.hit.common;

import java.io.Serializable;

public class Response implements Serializable {

    private String status;
    private Object data;

    public Response(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{status='" + status + "', data=" + data + "}";
    }
}