package com.rakeshv.kafkaelasticsearchconnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.Date;

@SpringBootApplication
@EnableReactiveElasticsearchRepositories
@EnableBinding(KafkaTopicListener.class)
public class KafkaElasticsearchConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaElasticsearchConnectorApplication.class, args);
    }

}

@Component
@Slf4j
class TestSampleLog {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    KafkaTopicListener kafkaTopicListener;

    @Value("${spring.application.name}")
    private String appName;

    @EventListener(ApplicationReadyEvent.class)
    public void log() throws JsonProcessingException {
        Message message = Message.builder()
                .appName(appName)
                .message("testing from kafka")
                .timestamp(new Date())
                .build();

        try {
            Thread.sleep(5000);
            MessageChannel messageChannel = kafkaTopicListener.messageChannel();
            messageChannel.send(MessageBuilder.withPayload(message)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
        } catch (Exception e) {}
    }
}
