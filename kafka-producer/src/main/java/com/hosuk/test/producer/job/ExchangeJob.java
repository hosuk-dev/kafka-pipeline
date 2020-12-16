package com.hosuk.test.producer.job;

import com.hosuk.test.common.dto.ExchangeDto;
import com.hosuk.test.common.dto.SendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeJob {

    private final KafkaTemplate<String, ExchangeDto> exchangeJobKafkaTemplate;
    private final Environment env;

    //@Scheduled(fixedDelay = 1000)
    public void run () {
        String cpnNo = RandomStringUtils.randomAlphanumeric(8);
        ExchangeDto exchangeDto = ExchangeDto.registExchange(cpnNo, "S", LocalDateTime.now(), "교환정보");

        ListenableFuture<SendResult<String, ExchangeDto>> future =
                exchangeJobKafkaTemplate.send(env.getProperty("exchange.topic-name"), exchangeDto);

        future.addCallback((success) -> sendOnSuccess(success), (fail) -> sendOnFail(fail));
    }

    /**
     * Produce 성공 Callback
     *
     * @param sendResult
     */
    private void sendOnSuccess(SendResult<String, ExchangeDto> sendResult) {
       // log.info(sendResult.toString());

    }

    /**
     * Produce 실패 Callback
     *
     * @param ex
     */
    private void sendOnFail(Throwable ex) {
        log.info(ex.toString());
    }

}
