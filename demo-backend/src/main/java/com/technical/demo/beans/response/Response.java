package com.technical.demo.beans.response;

public class Response<T> {

    private Estados status;
    private T data;

    public Estados getStatus() {
        return status;
    }

    public void setStatus(Estados status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
