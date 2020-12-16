package com.hosuk.test.consumer.listener;

import com.hosuk.test.common.dto.SendDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendListener {

    @KafkaListener(topics = "${send.topic-name}",
            containerFactory = "sendDtoConcurrentKafkaListenerContainerFactory")
    public void sendTopicListener(SendDto sendDto) {
        log.info("[SendTopic] {}", sendDto.toString());
    }
}
