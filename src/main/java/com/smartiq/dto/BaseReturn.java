package com.smartiq.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BaseReturn<T> {

    private String code;

    private String message;

    private T data;

}
