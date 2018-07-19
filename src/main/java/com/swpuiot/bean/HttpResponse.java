package com.swpuiot.bean;

import java.util.ArrayList;
import java.util.List;

public  class HttpResponse<T> {
    int HttpCode;
    List<T> list = new ArrayList<T>();

    public int getHttpCode() {
        return HttpCode;
    }

    public void setHttpCode(int httpCode) {
        HttpCode = httpCode;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
