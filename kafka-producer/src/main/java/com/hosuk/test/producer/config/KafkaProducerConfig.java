package com.hosuk.test.producer.config;

import com.hosuk.test.common.dto.ExchangeDto;
import com.hosuk.test.common.dto.SendDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    /**
     * Json Serializer producer config 설정
     *
     * @return
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getProducer().getAcks());
        config.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getProducer().getRetries());
        return config;
    }

    /**
     * SendDto Producer Factory
     * @return
     */
    @Bean
    public ProducerFactory<String, SendDto> sendDtoProducerFactory() {
        Map<String, Object> config = producerConfig();
        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * Exchange Producer Factory
     * @return
     */
    @Bean
    public ProducerFactory<String, ExchangeDto> exchangeDtoProducerFactory() {
        Map<String, Object> config = producerConfig();
        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * SendDto Template
     *
     * @return
     */
    @Bean
    public KafkaTemplate<String, SendDto> sendDtoTemplate() {
        return new KafkaTemplate<>(sendDtoProducerFactory());
    }

    /**
     * Exchange Template
     *
     * @return
     */
    @Bean
    public KafkaTemplate<String, ExchangeDto> exchangeDtoTemplate() {
        return new KafkaTemplate<>(exchangeDtoProducerFactory());
    }

}
