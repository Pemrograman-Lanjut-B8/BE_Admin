package id.ac.ui.cs.advprog.admin.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private String status;
    private String message;
    private T data;

    public ResponseDto(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
