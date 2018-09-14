package com.zhuochen.spring.session.web.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
public class WrappedResponse<T> {

    @ApiModelProperty(value = "response data")
    private T data;

    @ApiModelProperty(value = "response status")
    private int status = HttpStatus.OK.value();

    @ApiModelProperty(value = "response message")
    private String message = HttpStatus.OK.getReasonPhrase();

    /**
     * success response wrapper
     *
     * @param data
     */
    public WrappedResponse(T data) {
        this.data = data;
    }

    /**
     * error response wrapper
     *
     * @param status
     * @param message
     */
    public WrappedResponse(@Nullable T data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public WrappedResponse(@Nullable T data, HttpStatus status) {
        this.data = data;
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public WrappedResponse(@Nullable T data, HttpStatus status, String message) {
        this.data = data;
        this.status = status.value();
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrappedResponse{" +
                "data=" + data +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
