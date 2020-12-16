package com.hosuk.test.consumer.listener;

import com.hosuk.test.common.dto.ExchangeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExchangeListener {

    @KafkaListener(topics = "${exchange.topic-name}",
            containerFactory = "exchangeDtoConcurrentKafkaListenerContainerFactory")
    public void exchangeTopicListener(ExchangeDto exchangeDto) {
        log.info("[ExchangeTopic] {}", exchangeDto.toString());
    }

}
