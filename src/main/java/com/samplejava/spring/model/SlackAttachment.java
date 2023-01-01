package com.samplejava.spring.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SlackAttachment {

    private String text;
    private List<SlackField> fields;
}