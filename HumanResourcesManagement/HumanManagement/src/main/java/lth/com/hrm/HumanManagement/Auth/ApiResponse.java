package lth.com.hrm.HumanManagement.Auth;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private Object data;

    public ApiResponse(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }
    public ApiResponse(String message){
        this.message = message;
    }

    public ApiResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse [statusCode=" + status + ", message=" + message +"]";
    }

}
