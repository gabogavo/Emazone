package com.bootcampPragma.EmazonTest01.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequest {
    private Long id;
    private String name;
    private String description;
}
