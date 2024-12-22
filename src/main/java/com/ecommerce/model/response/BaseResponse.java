package com.ecommerce.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private Long total;
    private Integer page;
    private Integer limit;
}
