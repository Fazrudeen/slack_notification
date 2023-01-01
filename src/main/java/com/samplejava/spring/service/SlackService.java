package com.samplejava.spring.service;

import com.samplejava.spring.config.AppConfig;
import com.samplejava.spring.model.SlackAttachment;
import com.samplejava.spring.model.SlackField;
import com.samplejava.spring.model.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SlackService {

    private static final Logger logger = LoggerFactory.getLogger(SlackService.class);

    @Autowired
    private AppConfig appConfig;

    @Async
    public void sendMessage(String errorMessage, String className, String methodName) {

        if (appConfig.isSlackEnabled()) {

            List<SlackAttachment> attachments = new ArrayList<>();
            List<SlackField> fields = new ArrayList<>();
            try {
                fields.add(SlackField.builder().title("Host Name").value(InetAddress.getLocalHost().getHostName()).isShort(true).build());
                fields.add(SlackField.builder().title("Time").value(new java.util.Date(System.currentTimeMillis()).toString()).isShort(true).build());
                fields.add(SlackField.builder().title("Class Name").value(className).isShort(true).build());
                fields.add(SlackField.builder().title("Method Name").value(methodName).isShort(true).build());
                fields.add(SlackField.builder().title("Error cause").value(errorMessage).isShort(false).build());

            } catch (UnknownHostException e) {
                logger.error("Exception occurred while fetching IP Address",e);
            }

            attachments.add(SlackAttachment.builder().text("Exception in *Sample Service*").fields(fields).build());

            SlackMessage slackMessage = SlackMessage.builder()
                    .channel(appConfig.getSlackChannel())
                    .username(appConfig.getSlackUsername())
                    .text("Exception in *Sample Service*")
                    .attachments(attachments)
                    .build();

            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<SlackMessage> entity = new HttpEntity<>(slackMessage, headers);

            ResponseEntity<String> response = template.exchange(appConfig.getSlackUrl(), HttpMethod.POST, entity, String.class);
            logger.info("Slack API Response code :: {}",response.getStatusCode());
        }
    }
}