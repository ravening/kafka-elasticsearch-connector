package com.rakeshv.kafkaelasticsearchconnector;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaTopicListener {
    String input = "topics";
    @Input(input)
    SubscribableChannel topicsListener();

    @Output(input)
    MessageChannel messageChannel();
}
