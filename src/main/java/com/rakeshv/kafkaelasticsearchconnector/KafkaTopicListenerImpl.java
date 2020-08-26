package com.rakeshv.kafkaelasticsearchconnector;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaTopicListenerImpl {
    private final MessageRepository messageRepository;
    ObjectMapper mapper = new ObjectMapper();

    @Async
    @StreamListener(KafkaTopicListener.input)
    public void handleTopicMessage(String message) throws IOException {
        Message msg = mapper.readValue(message, Message.class);
        messageRepository.save(msg)
        .subscribe();
    }
}
