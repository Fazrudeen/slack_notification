package com.samplejava.spring.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SlackMessage {

    private String channel;
    private String username;
    private String text;
    private String icon_url;
    private List<SlackAttachment> attachments;
}