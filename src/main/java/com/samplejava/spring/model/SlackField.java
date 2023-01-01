package com.samplejava.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlackField {

    private String title;
    private String value;

    @JsonProperty("short")
    private boolean isShort;
}