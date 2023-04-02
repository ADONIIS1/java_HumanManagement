package lth.com.hrm.HumanManagement.RequestBodyValid;

import java.util.List;

public class RequestBodyData <T>{
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> requestBody) {
        this.data = requestBody;
    }

    public RequestBodyData(List<T> requestBody) {
        this.data = requestBody;
    }

    public RequestBodyData() {
    }
}
