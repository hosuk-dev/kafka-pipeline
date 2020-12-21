package com.hosuk.test.consumer.config;

import com.hosuk.test.common.dto.ExchangeDto;
import com.hosuk.test.common.dto.SendDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    /**
     * SendDto ConsumerFactory
     *
     * @return
     */
    public ConsumerFactory<String, SendDto> sendDtoConsumerConfig() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(SendDto.class));
    }

    /**
     * SendDto Listener Factory
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SendDto> sendDtoConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SendDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sendDtoConsumerConfig());
        return factory;
    }

    /**
     * ExchangeDto ConsumerFactory
     *
     * @return
     */
    public ConsumerFactory<String, ExchangeDto> exchangeDtoConsumerConfig() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(ExchangeDto.class));
    }

    /**
     * ExchangeDto Listener Factory
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExchangeDto> exchangeDtoConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExchangeDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(exchangeDtoConsumerConfig());
        return factory;
    }


}
