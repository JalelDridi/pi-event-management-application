package com.esprit.pidev.resourcemodule.dto;

import lombok.Data;

@Data
public class StatisticDto {
    private String resourceTypeName;
    private Long resourceCount;

    public StatisticDto(String resourceTypeName, Long resourceCount) {
        this.resourceTypeName = resourceTypeName;
        this.resourceCount = resourceCount;
    }

}
