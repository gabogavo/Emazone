package com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateBrandRequest {
    private final Long id;
    private final String name;
    private final String description;
}
