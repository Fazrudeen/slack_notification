package com.samplejava.spring.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {

    @Value("${notification.slack.url}")
    private String slackUrl;

    @Value("${notification.slack.channel}")
    private String slackChannel;

    @Value("${notification.slack.enabled}")
    private boolean slackEnabled;

    @Value("${notification.slack.username}")
    private String slackUsername;

}