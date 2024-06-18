package com.poscodx.mysite.dto;

import lombok.Data;

@Data
public class JsonResult {
    private String result;  // "success" or "fail"
    private String message;
    private Object data;
    private JsonResult(Object data) {
        result = "success";
        this.data = data;
    }
    private JsonResult(String message) {
        result = "fail";
        this.data = message;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult fail(String message) {
        return new JsonResult(message);
    }
}
