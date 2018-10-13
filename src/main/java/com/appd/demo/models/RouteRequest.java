package com.appd.demo.models;

import com.google.gson.JsonElement;

import java.util.Enumeration;
import java.util.HashMap;

public class RouteRequest {

    private String method;
    private JsonElement data;
    private HashMap<String, String> headers;

    public RouteRequest(){
        this.headers = new HashMap<String, String>();
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }


}
